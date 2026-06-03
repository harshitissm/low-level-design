import entities.Plan;
import entities.RequestContext;
import factory.PolicyBuilder;
import services.RateLimiterService;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // ─────────────────────────────────────────────────────────
        // LEGACY demo (unchanged) — per-userId, strategy only
        // ─────────────────────────────────────────────────────────
        System.out.println("=== Legacy userId-based demo ===");
        RateLimiterService service = new RateLimiterService();

        service.registerUser("user_1", "fixed", 5, 10);
        service.registerUser("user_2", "sliding", 3, 5);
        service.registerUser("user_3", "token-bucket", 5, 10);
        service.registerUser("user_4", "leaky-bucket", 3, 4);
        service.registerUser("user_5", "sliding-window-counter", 4, 6);

        for (int i = 0; i < 7; i++) {
            System.out.println("User 1 Request " + (i + 1) + " : " + service.allowRequest("user_1"));
            System.out.println("User 2 Request " + (i + 1) + " : " + service.allowRequest("user_2"));
            System.out.println("User 3 Request " + (i + 1) + " : " + service.allowRequest("user_3"));
            System.out.println("User 4 Request " + (i + 1) + " : " + service.allowRequest("user_4"));
            System.out.println("User 5 Request " + (i + 1) + " : " + service.allowRequest("user_5"));
            Thread.sleep(1000);
        }

        // ─────────────────────────────────────────────────────────
        // NEW demo — dynamic per-user policies
        // ─────────────────────────────────────────────────────────
        System.out.println("\n=== Dynamic policy demo ===");

        // alice: rate-limited by userId only (5 req / 10 sec, sliding window)
        service.registerPolicy("alice", PolicyBuilder.byUserId("sliding", 5, 10));

        // bob: rate-limited by IP only (3 req / 10 sec, fixed window)
        // Useful when you don't trust the userId (e.g., unauthenticated endpoint)
        service.registerPolicy("bob", PolicyBuilder.byIp("fixed", 3, 10));

        // charlie: rate-limited by API key only (4 req / 10 sec, token bucket)
        service.registerPolicy("charlie", PolicyBuilder.byApiKey("token-bucket", 4, 10));

        // dave: IP check AND userId check — both must pass
        // IP cap = 5 req/10s (protects against shared-IP abuse)
        // User cap = 3 req/10s (per-user personal quota)
        service.registerPolicy("dave", PolicyBuilder.byIpAndUserId(
                "fixed",   5, 10,   // IP limit
                "sliding", 3, 10    // userId limit
        ));

        // eve: plan-based — each user gets their OWN bucket sized by their plan
        // FREE=10/min, PRO=100/min, ENTERPRISE=1000/min
        service.registerPolicy("eve-free",       PolicyBuilder.byUserWithPlan(Plan.FREE));
        service.registerPolicy("eve-pro",        PolicyBuilder.byUserWithPlan(Plan.PRO));
        service.registerPolicy("eve-enterprise", PolicyBuilder.byUserWithPlan(Plan.ENTERPRISE));

        // ── alice: userId-based (5 req/10s) — expect first 5 allowed, then denied ──
        System.out.println("\n-- alice (userId-based, 5 req/10s) --");
        RequestContext aliceCtx = new RequestContext("alice", "10.0.0.1", null, Plan.FREE);
        for (int i = 0; i < 7; i++) {
            System.out.println("  request " + (i + 1) + ": " + service.allowRequest("alice", aliceCtx));
        }

        // ── bob: IP-based (3 req/10s) — expect first 3 allowed ──
        System.out.println("\n-- bob (IP-based, 3 req/10s) --");
        RequestContext bobCtx = new RequestContext(null, "192.168.1.5", null, null);
        for (int i = 0; i < 5; i++) {
            System.out.println("  request " + (i + 1) + ": " + service.allowRequest("bob", bobCtx));
        }

        // ── charlie: API-key-based (4 req/10s) ──
        System.out.println("\n-- charlie (API-key-based, 4 req/10s) --");
        RequestContext charlieCtx = new RequestContext(null, null, "key-xyz-999", null);
        for (int i = 0; i < 6; i++) {
            System.out.println("  request " + (i + 1) + ": " + service.allowRequest("charlie", charlieCtx));
        }

        // ── dave: IP(5) AND userId(3) — userId quota is the binding constraint ──
        System.out.println("\n-- dave (IP-cap=5 AND userId-cap=3 per 10s) --");
        RequestContext daveCtx = new RequestContext("dave", "10.0.0.99", null, null);
        for (int i = 0; i < 6; i++) {
            System.out.println("  request " + (i + 1) + ": " + service.allowRequest("dave", daveCtx));
        }

        // ── plan-based: each user has their own bucket ──
        System.out.println("\n-- plan-based (FREE=10/min, PRO=100/min) — each user owns their bucket --");
        RequestContext freePlanCtx = new RequestContext("eve-free",       null, null, Plan.FREE);
        RequestContext proPlanCtx  = new RequestContext("eve-pro",        null, null, Plan.PRO);
        for (int i = 0; i < 12; i++) {
            boolean freeAllowed = service.allowRequest("eve-free", freePlanCtx);
            boolean proAllowed  = service.allowRequest("eve-pro",  proPlanCtx);
            System.out.println("  request " + (i + 1) + "  FREE=" + freeAllowed + "  PRO=" + proAllowed);
        }
    }
}
