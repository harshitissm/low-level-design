import service.VersionControlService;
import service.VersionControlServiceImpl;

public class Main {

    public static void main(String[] args) {

        VersionControlService vcs = new VersionControlServiceImpl();

        vcs.init();

        vcs.commit("Initial Commit");

        vcs.commit("Payment API");

        vcs.createBranch("feature");

        vcs.checkout("feature");

        vcs.commit("UPI Support");

        vcs.commit("Refund API");

        vcs.log();

        vcs.checkout("main");

        vcs.log();
    }
}