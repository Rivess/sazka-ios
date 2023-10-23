package sazka.ios.test.base.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface Locator {

    String value() default "";

    LocatorType type() default LocatorType.ID;

    enum LocatorType {
        ID,
        XPATH;

        public boolean is(LocatorType locatorType) {
            return this == locatorType;
        }
    }
}
