package sazka.ios.test.base;

import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.base.errors.CannotInstantiateComponent;
import sazka.ios.test.base.errors.CannotInstantiateObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ObjectFactory {

    public static <T extends PageObject> T createObject(Driver driver, Class<T> proxyClass) {
        T pageObject;
        try {
            pageObject = proxyClass.getConstructor(Driver.class).newInstance(driver);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new CannotInstantiateObject("Could not instantiate page object by class: %s. Original exception: %s", proxyClass.getName(), e.getMessage());
        }
        return pageObject;
    }
}
