package sazka.ios.test.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sazka.ios.test.base.driver.Driver;

import java.time.Duration;
import java.util.List;

public abstract class PageComponent implements WebElement {
    protected final int DEFAULT_TIMEOUT = 60;
    protected Driver driver;
    private String locator;

    public PageComponent(Driver driver) {
        this.driver = driver;
        LocatorProcessor.processLocatorAnnotations(this);
    }

    @Override
    public void click() {
        new WebDriverWait(driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(this.getLocator())))
                .click();
    }

    @Override public void submit() {

    }

    @Override public void sendKeys(CharSequence... keysToSend) {

    }

    @Override public void clear() {

    }

    @Override public String getTagName() {
        return null;
    }

    @Override public String getDomProperty(String name) {
        return null;
    }

    @Override public String getDomAttribute(String name) {
        return null;
    }

    @Override public String getAttribute(String name) {
        return null;
    }

    @Override public String getAriaRole() {
        return null;
    }

    @Override public String getAccessibleName() {
        return null;
    }

    @Override public boolean isSelected() {
        return false;
    }

    @Override public boolean isEnabled() {
        return false;
    }

    @Override public String getText() {
        return null;
    }

    @Override public List<WebElement> findElements(By by) {
        return null;
    }

    @Override public WebElement findElement(By by) {
        return null;
    }

    @Override public SearchContext getShadowRoot() {
        return null;
    }

    @Override public boolean isDisplayed() {
        return false;
    }

    @Override public Point getLocation() {
        return null;
    }

    @Override public Dimension getSize() {
        return null;
    }

    @Override public Rectangle getRect() {
        return null;
    }

    @Override public String getCssValue(String propertyName) {
        return null;
    }

    @Override public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

    public Driver getDriver() {
        return driver;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }
}
