package sazka.ios.test.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.base.errors.IOSTestException;

import java.time.Duration;
import java.util.List;

public abstract class PageComponent implements WebElement {
    protected final int DEFAULT_TIMEOUT = 60;
    protected Driver driver;
    private String locator;
    private Locator.LocatorType locatorType;

    public PageComponent(Driver driver) {
        this.driver = driver;
        LocatorProcessor.processLocatorAnnotations(this);
    }

    private By getByLocator() {
        By locator;
        if (this.locatorType.is(Locator.LocatorType.ID)) {
            locator = By.id(this.getLocator());
        } else {
            locator = By.xpath(this.getLocator());
        }
        return locator;
    }

    private WebElement find() {
        List<WebElement> elements = this.findElements(this.getByLocator());
        if (elements.isEmpty()) {
            throw new IOSTestException("Could not find any elements by locator: %s", this.getByLocator().toString());
        }
        return elements.get(0);
    }

    @Override
    public void click() {
        new WebDriverWait(driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(this.getByLocator()))
                .click();
    }

    @Override
    public void submit() {
        new WebDriverWait(driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(this.getByLocator()))
                .submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        new WebDriverWait(driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(this.getByLocator()))
                .sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        new WebDriverWait(driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(this.getByLocator()))
                .clear();
    }

    @Override
    public String getTagName() {
        return this.find().getTagName();
    }

    @Override
    public String getDomProperty(String name) {
        return this.find().getDomProperty(name);
    }

    @Override
    public String getDomAttribute(String name) {
        return this.find().getDomAttribute(name);
    }

    @Override public String getAttribute(String name) {
        return this.find().getAttribute(name);
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

    @Override
    public List<WebElement> findElements(By by) {
        return driver.getRemoteWebDriver().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return driver.getRemoteWebDriver().findElement(by);
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

    public Locator.LocatorType getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(Locator.LocatorType locatorType) {
        this.locatorType = locatorType;
    }
}