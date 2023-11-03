package sazka.ios.test.base.errors;

public class IOSTestException extends RuntimeException {
    public IOSTestException(String errorMessage) {
        super(errorMessage);
    }

    public IOSTestException(String errorMessage, Object... args) {
        super(String.format(errorMessage, args));
    }
}
