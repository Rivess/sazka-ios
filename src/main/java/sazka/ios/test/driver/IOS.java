package sazka.ios.test.driver;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class IOS extends Driver {

    @Override
    public void createDriver() {
        XCUITestOptions options = new XCUITestOptions()
                .setUdid("00008101-0016684A3406001E")
                .setApp("/home/myapp.ipa");
        try {
            driver = new IOSDriver(
                    // The default URL in Appium 1 is http://127.0.0.1:4723/wd/hub
                    new URL("http://127.0.0.1:4723"), options
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
