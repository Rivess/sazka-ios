package sazka.ios.test.base.driver;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class IOS extends Driver {

    @Override
    public void createDriver() {
        XCUITestOptions options = new XCUITestOptions()
                .setUdid("22d2425553e7b83298fa70d711f5758f72684cd1");
        try {
            driver = new IOSDriver(
                    new URL(System.getProperty), options
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void activateApp() {
        getDriver().activateApp("cz.Sazka.Sazka");
    }

    @Override
    public IOSDriver getDriver() {
        return (IOSDriver) driver;
    }
}
