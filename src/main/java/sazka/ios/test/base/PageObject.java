package sazka.ios.test.base;

import sazka.ios.test.base.driver.Driver;

public abstract class PageObject {
    protected Driver driver;

    public Driver getDriver() {
        return driver;
    }

    public PageObject(Driver driver) {
        this.driver = driver;
        ObjectFactory.setupPageComponents(this.driver, this);
    }
}
