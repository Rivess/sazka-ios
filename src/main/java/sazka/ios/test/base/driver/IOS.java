package sazka.ios.test.base.driver;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.apache.logging.log4j.Level;
import org.testng.ITestContext;
import sazka.ios.test.base.errors.IOSTestException;
import sazka.ios.test.data.Device;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;

public class IOS extends Driver {
    private Device device;

    public IOS() {
        super();
    }

    @Override
    public void createDriver() {
        device = this.createDeviceFromXml();
        logger.printf(Level.INFO, "Created device from xml with values: %s", device.toString());
        XCUITestOptions options = new XCUITestOptions()
                .setUdid(device.getUdid());
        logger.printf(Level.INFO, "Creating XCUITestOptions with capabilities: %s", options.asMap().toString());
        try {
            logger.printf(Level.INFO, "Connecting IOSDriver to server with url: %s", properties.getProperty("appium_url"));
            driver = new IOSDriver(
                    new URL(properties.getProperty("appium_url")), options
            );
        } catch (MalformedURLException e) {
            throw new IOSTestException("Could not connect to appium server! Original exception message: %s", e.getMessage());
        }
    }

    @Override
    public void activateApp() {
        logger.printf(Level.INFO, "Activating app with bundle id: %s", properties.getProperty("bundle_id"));
        getDriver().activateApp(properties.getProperty("bundle_id"));
    }

    @Override
    public IOSDriver getDriver() {
        return (IOSDriver) driver;
    }

    private Device createDeviceFromXml() {
        if (!System.getProperties().containsKey("device")) {
            throw new IOSTestException("You have to select device to run tests on! Select device by parameter -Ddevice=filename");
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Device.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Device) unmarshaller.unmarshal(Thread.currentThread().getContextClassLoader().getResourceAsStream(System.getProperty("device")));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private Device getDevice() {
        return this.device;
    }

    public void reset() {
        logger.printf(Level.INFO, "Terminating app and quiting driver!");
        this.terminateApp();
        this.getDriver().quit();
    }

    public void terminateApp() {
        this.getDriver().terminateApp(properties.getProperty("bundle_id"));
    }
}
