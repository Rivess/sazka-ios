package sazka.ios.test.base.errors;

public class CannotInstantiateObject extends RuntimeException {

    public CannotInstantiateObject(String errorMessage) {
        super(errorMessage);
    }

    public CannotInstantiateObject(String errorMessage, Object... args) {
        super(String.format(errorMessage, args));
    }
}
