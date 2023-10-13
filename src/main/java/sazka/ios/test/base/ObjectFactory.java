package sazka.ios.test.base;

import sazka.ios.test.base.driver.Driver;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ObjectFactory {

    public static <T extends PageObject> T createObject(Driver driver, Class<T> tClass) {
        T pageObject;
        try {
            pageObject = tClass.getConstructor(Driver.class).newInstance(driver);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return pageObject;
    }

    private static <T extends PageComponent> T createComponent(Driver driver, Field field) {
        PageComponent pageComponent;
        try {
            pageComponent = field.getType().asSubclass(PageComponent.class).getConstructor(Driver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        LocatorProcessor.processLocatorAnnotations(pageComponent, field);
        return (T) pageComponent;
    }

    public static void setupPageComponents(Driver driver, PageObject pageObject) {
        PageComponent pageComponent;
        for (Field field : pageObject.getClass().getDeclaredFields()) {
            if (PageComponent.class.isAssignableFrom(field.getType())) {
                pageComponent = createComponent(driver, field);
                try {
                    field.setAccessible(true);
                    field.set(pageObject, pageComponent);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
