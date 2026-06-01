import ratelimiter.clock.Clock;
import ratelimiter.clock.SystemClock;
import ratelimiter.model.RateLimitDecision;
import ratelimiter.model.TierPolicy;
import ratelimiter.model.UserTier;
import ratelimiter.service.RateLimiterService;

public class Main {

    public static void main(String[] args) {
        Clock clock = new SystemClock();
        RateLimiterService service = new RateLimiterService(clock);

        service.setTierPolicy(
                UserTier.NORMAL,
                TierPolicy.forSlidingWindow(3, 10)
        );
        service.setTierPolicy(
                UserTier.PREMIUM,
                TierPolicy.forTokenBucket(10, 5.0)
        );

        service.setUserTier("alice", UserTier.NORMAL);
        service.setUserTier("bob", UserTier.PREMIUM);

        long now = clock.nowSeconds();
        System.out.println("== Initial policies ==");
        for (int i = 1; i <= 4; i++) {
            RateLimitDecision d = service.allow("alice", now);
            System.out.printf("alice req %d -> %s%n", i, d);
        }

        for (int i = 1; i <= 12; i++) {
            RateLimitDecision d = service.allow("bob", now);
            System.out.printf("bob req %d -> %s%n", i, d);
        }

        System.out.println("\n== Runtime switch: NORMAL -> Token Bucket ==");
        service.setTierPolicy(
                UserTier.NORMAL,
                TierPolicy.forTokenBucket(6, 2.0)
        );
        for (int i = 1; i <= 8; i++) {
            RateLimitDecision d = service.allow("alice", now);
            System.out.printf("alice req %d after switch -> %s%n", i, d);
        }
    }
}
