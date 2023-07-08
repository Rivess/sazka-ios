package sazka.ios.test;

import org.testng.annotations.BeforeSuite;
import sazka.ios.test.driver.IOS;

public class IOSTest {
    protected IOS driver;

    @BeforeSuite
    public void installApp() {
        this.startDriver();
    }

    private void startDriver() {
        driver = new IOS();
    }
}
