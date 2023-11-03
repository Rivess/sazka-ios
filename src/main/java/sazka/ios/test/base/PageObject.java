package sazka.ios.test.base;

import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.base.errors.CannotInstantiateComponent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public abstract class PageObject {
    protected Driver driver;

    public Driver getDriver() {
        return driver;
    }

    public PageObject(Driver driver) {
        this.driver = driver;
        this.setupPageComponents(driver, this);
    }

    private void setupPageComponents(Driver driver, PageObject pageObject) {
        PageComponent pageComponent;
        for (Field field : pageObject.getClass().getDeclaredFields()) {
            if (PageComponent.class.isAssignableFrom(field.getType())) {
                pageComponent = this.createComponent(driver, field);
                try {
                    field.setAccessible(true);
                    field.set(pageObject, pageComponent);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private PageComponent createComponent(Driver driver, Field field) {
        PageComponent pageComponent;
        try {
            pageComponent = field.getType().asSubclass(PageComponent.class).getConstructor(Driver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new CannotInstantiateComponent("Could not instantiate page component by name: %s. Original exception: %s", field.getName(), e.getMessage());
        }
        LocatorProcessor.processLocatorAnnotations(pageComponent, field);
        return pageComponent;
    }
}
