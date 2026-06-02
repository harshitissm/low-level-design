package service;

import exception.BranchAlreadyExistsException;
import exception.BranchNotFoundException;
import exception.RepositoryNotInitializedException;
import model.Branch;
import model.Commit;
import model.Repository;
import util.IdGenerator;

public class VersionControlServiceImpl implements VersionControlService {

    private Repository repository;

    @Override
    public void init() {
        repository = new Repository();

        Branch mainBranch = new Branch("main", null);

        repository.getBranches().put("main", mainBranch);

        repository.setCurrentBranch(mainBranch);

        System.out.println("Repository initialized");
    }

    @Override
    public void commit(String message) {
        validateRepository();

        Branch current = repository.getCurrentBranch();

        Commit parent = current.getHead();

        Commit commit = new Commit(
                IdGenerator.generateCommitId(),
                message,
                parent
        );

        current.setHead(commit);

        System.out.println("Commit created: " + commit.getId());
    }

    @Override
    public void createBranch(String branchName) {
        validateRepository();

        if (repository.getBranches().containsKey(branchName)) {
            throw new BranchAlreadyExistsException("Branch already exists");
        }

        Branch current = repository.getCurrentBranch();

        Branch newBranch = new Branch(branchName, current.getHead());

        repository.getBranches().put(branchName, newBranch);

        System.out.println("Branch created: " + branchName);
    }

    @Override
    public void checkout(String branchName) {
        validateRepository();

        Branch branch = repository.getBranches().get(branchName);

        if (branch == null) {
            throw new BranchNotFoundException(branchName + " not found");
        }

        repository.setCurrentBranch(branch);

        System.out.println("Switched to branch " + branchName);
    }

    @Override
    public void log() {
        validateRepository();

        Commit commit = repository.getCurrentBranch().getHead();

        while (commit != null) {
            System.out.println(commit.getId() + " | " + commit.getMessage());
            commit = commit.getParent();
        }
    }

    private void validateRepository() {
        if (repository == null) {
            throw new RepositoryNotInitializedException("Run init first");
        }
    }
}