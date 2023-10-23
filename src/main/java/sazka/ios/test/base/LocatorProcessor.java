package sazka.ios.test.base;

import sazka.ios.test.base.annotations.Locator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class LocatorProcessor {

    public static void processLocatorAnnotations(PageComponent pageComponent) {
        Annotation[] annotations = pageComponent.getClass().getDeclaredAnnotations();
        processLocatorAnnotations(pageComponent, annotations);
    }

    public static void processLocatorAnnotations(PageComponent pageComponent, Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        processLocatorAnnotations(pageComponent, annotations);
    }

    private static void processLocatorAnnotations(PageComponent pageComponent, Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Locator.class)) {
                Locator locator = (Locator) annotation;
                pageComponent.setLocatorType(locator.type());
                pageComponent.setLocator(locator.value());
            }
        }
    }
}
