package exception;

public class RepositoryNotInitializedException extends RuntimeException {
    public RepositoryNotInitializedException(String msg) {
        super(msg);
    }
}