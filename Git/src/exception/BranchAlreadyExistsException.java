package exception;

public class BranchAlreadyExistsException extends RuntimeException {
    public BranchAlreadyExistsException(String msg) {
        super(msg);
    }
}