package exception;

/**
 * Exception thrown when a script returns exitCode != 0.
 */
public class ScriptException extends Exception {

    public ScriptException() {}

    public ScriptException(String s) {
        super(s);
    }

    public ScriptException(String s, Throwable e) {
        super(s, e);
    }
}
