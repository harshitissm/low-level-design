package model;

import java.util.HashMap;
import java.util.Map;

public class Repository {

    private final Map<String, Branch> branches;
    private Branch currentBranch;

    public Repository() {
        this.branches = new HashMap<>();
    }

    public Map<String, Branch> getBranches() {
        return branches;
    }

    public Branch getCurrentBranch() {
        return currentBranch;
    }

    public void setCurrentBranch(Branch currentBranch) {
        this.currentBranch = currentBranch;
    }
}