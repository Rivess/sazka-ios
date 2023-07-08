package sazka.ios.test.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public class IOS extends Driver {

    @Override
    public void createDriver() {
        //        XCUITestOptions options = new XCUITestOptions()
        //                .setUdid("00008101-0016684A3406001E")
        //                .setApp("/home/myapp.ipa");
        //        try {
        //            driver = new IOSDriver(
        //                    new URL("http://127.0.0.1:4723"), options
        //            );
        //        } catch (MalformedURLException e) {
        //            e.printStackTrace();
        //        }
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("672fd800");
        try {
            driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723"), options
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void activateApp() {
        getDriver().activateApp("cz.sazka.loterie.uat");
    }

    @Override
    public AndroidDriver getDriver() {
        return (AndroidDriver) driver;
    }
}
