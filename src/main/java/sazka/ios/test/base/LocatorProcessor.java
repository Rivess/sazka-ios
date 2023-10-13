package sazka.ios.test.base;

import sazka.ios.test.base.annotations.Locator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class LocatorProcessor {

    public static void processLocatorAnnotations(PageComponent pageComponent) {
        Annotation[] annotation = pageComponent.getClass().getDeclaredAnnotations();
        Locator locator = (Locator) annotation[0];
        pageComponent.setLocator(locator.id());
    }

    public static void processLocatorAnnotations(PageComponent pageComponent, Field field) {
        Annotation[] annotation = field.getClass().getDeclaredAnnotations();
        Locator locator = (Locator) annotation[0];
        pageComponent.setLocator(locator.id());
    }
}
