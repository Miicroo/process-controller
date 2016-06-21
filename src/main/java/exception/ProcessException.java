package exception;

/**
 * Exception thrown when vagrant or a vagrant command returns exitCode != 0.
 */
public class ProcessException extends Exception {

    public ProcessException() {}

    public ProcessException(String s) {
        super(s);
    }

    public ProcessException(String s, Throwable e) {
        super(s, e);
    }
}
