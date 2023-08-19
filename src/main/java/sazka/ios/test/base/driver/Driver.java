package sazka.ios.test.base.driver;

import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class Driver {
    protected RemoteWebDriver driver;

    public Driver() {
        this.createDriver();
    }

    public abstract void createDriver();

    public RemoteWebDriver getRemoteWebDriver() {
        return driver;
    }

    public abstract void activateApp();

    public abstract RemoteWebDriver getDriver();
}
