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

    public static PageComponent createComponent(Driver driver, Field field) {
        PageComponent pageComponent;
        try {
            pageComponent = field.getType().asSubclass(PageComponent.class).getConstructor(Driver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new CannotInstantiateComponent("Could not instantiate page component by name: %s. Original exception: %s", field.getName(), e.getMessage());
        }
        LocatorProcessor.processLocatorAnnotations(pageComponent, field);
        return pageComponent;
    }

    @SuppressWarnings("unchecked")
    public static <T extends PageComponent> T createComponent(PageComponent pageComponent) {
        T newPageComponent;
        try {
            newPageComponent = (T) pageComponent.getClass().getConstructor(Driver.class).newInstance(pageComponent.getDriver());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new CannotInstantiateComponent("Could not instantiate page component by name: %s. Original exception: %s",
                                                 pageComponent.getClass().getName(),
                                                 e.getMessage());
        }
        return newPageComponent;
    }
}
