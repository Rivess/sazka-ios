package sazka.ios.test.base;

import org.apache.logging.log4j.Level;
import sazka.ios.test.base.driver.Driver;

import java.lang.reflect.Field;
import java.util.List;

public abstract class PageObject {
    protected Driver driver;

    public PageObject(Driver driver) {
        this.driver = driver;
        this.setupPageComponents(driver, this);
    }

    private void setupPageComponents(Driver driver, PageObject pageObject) {
        PageComponent pageComponent;
        List<Field> fields = this.getPageObjectFields(pageObject);
        for (Field field : fields) {
            if (PageComponent.class.isAssignableFrom(field.getType())) {
                pageComponent = ObjectFactory.createComponent(driver, field);
                try {
                    field.setAccessible(true);
                    field.set(pageObject, pageComponent);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private List<Field> getPageObjectFields(PageObject pageObject) {
        List<Field> fields = new java.util.ArrayList<>(List.of(pageObject.getClass().getDeclaredFields()));
        Class<?> superclass = pageObject.getClass().getSuperclass();
        while (superclass != null) {
            fields.addAll(List.of(superclass.getDeclaredFields()));
            superclass = superclass.getSuperclass();
        }

        return fields;
    }

    public void log(Level level, String msg, Object... args) {
        this.driver.log(level, msg, args);
    }
}
