package sazka.ios.test;

import org.testng.annotations.BeforeSuite;
import sazka.ios.test.base.driver.IOS;

public class IOSTest {
    protected IOS driver;
    protected final int DEFAULT_TIMEOUT = 60;

    @BeforeSuite
    public void setUp() {
        driver = new IOS();
        driver.activateApp();
    }
}
