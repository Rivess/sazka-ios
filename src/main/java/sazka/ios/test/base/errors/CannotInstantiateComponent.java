package sazka.ios.test.base.errors;

public class CannotInstantiateComponent extends RuntimeException {

    public CannotInstantiateComponent(String errorMessage) {
        super(errorMessage);
    }

    public CannotInstantiateComponent(String errorMessage, Object... args) {
        super(String.format(errorMessage, args));
    }
}
