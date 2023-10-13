package sazka.ios.test.base;

import sazka.ios.test.base.annotations.Locator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

public class LocatorProcessor {

    public static void processLocatorAnnotations(PageComponent pageComponent) {
        Annotation[] annotations = pageComponent.getClass().getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Locator.class)) {
                Locator locator = (Locator) annotation;
                pageComponent.setLocator(locator.id());
            }
        }
    }

    public static void processLocatorAnnotations(PageComponent pageComponent, Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Locator.class)) {
                Locator locator = (Locator) annotation;
                pageComponent.setLocator(locator.id());
            }
        }
    }
}
