package sazka.ios.test.base.driver;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class Driver {
    protected static final Logger logger = LogManager.getLogger("TestLogger");
    protected Properties properties;
    protected RemoteWebDriver driver;

    public Driver() {
        this.loadTestProperties();
        this.createDriver();
    }

    public abstract void createDriver();

    public RemoteWebDriver getRemoteWebDriver() {
        return driver;
    }

    public abstract void activateApp();

    public abstract RemoteWebDriver getDriver();

    public abstract void reset();

    private void loadTestProperties() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/test.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (System.getProperties().containsKey("appium_url")) {
            properties.setProperty("appium_url", System.getProperty("appium_url"));
        }
    }

    public void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveScreenShot() {
        logger.printf(Level.INFO, "Trying to take screenshot");
        String folder = ThreadContext.get("screenshotFolderName");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        try (FileOutputStream stream = new FileOutputStream(folder + "/" + formatter.format(now) + "_screenshot.jpg")) {
            stream.write(this.takeScreenShot());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] takeScreenShot() {
        return this.getDriver().getScreenshotAs(OutputType.BYTES);
    }

    public void log(Level level, String errorMessage, Object... args) {
        logger.printf(level, errorMessage, args);
    }

    private String getSource() {
        return this.driver.getPageSource();
    }

    public void saveSource() {
        logger.printf(Level.INFO, "Trying to save source");
        String folder = ThreadContext.get("screenshotFolderName");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        try (FileWriter out = new FileWriter(folder + "/" + formatter.format(now) + "_source.xml")) {
            out.write(this.getSource());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
