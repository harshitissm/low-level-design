package service;

public interface VersionControlService {

    void init();

    void commit(String message);

    void createBranch(String branchName);

    void checkout(String branchName);

    void log();
}