package sazka.ios.test.base;

import org.apache.logging.log4j.Level;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.base.errors.IOSTestException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class PageComponent implements WebElement {
    protected final int DEFAULT_TIMEOUT = 60;
    protected Driver driver;
    private String locator;
    private Locator.LocatorType locatorType;
    private WebElement webElement = null;

    public PageComponent(Driver driver) {
        this.driver = driver;
        LocatorProcessor.processLocatorAnnotations(this);
    }

    protected By getByLocator() {
        By locator;
        if (this.locatorType.is(Locator.LocatorType.ID)) {
            locator = By.id(this.getLocator());
        } else {
            locator = By.xpath(this.getLocator());
        }
        return locator;
    }

    private WebElement find() {
        driver.log(Level.INFO, "Finding element by Locator: %s", this.getByLocator());
        List<WebElement> elements = this.findElements(this.getByLocator());
        if (elements.isEmpty()) {
            throw new IOSTestException("Could not find any elements by locator: %s", this.getByLocator().toString());
        }
        return elements.get(0);
    }

    public <T extends PageComponent> List<T> findPageComponents(T pageComponent) {
        driver.log(Level.INFO, "Finding all elements by Locator: %s", this.getByLocator());
        List<T> pageComponents = new ArrayList<>();
        List<WebElement> elements = this.findElements(pageComponent.getByLocator());
        for (WebElement element : elements) {
            T newComponent = ObjectFactory.createComponent(pageComponent);
            newComponent.setWebElement(element);
            newComponent.setLocator(pageComponent.getLocator());
            newComponent.setLocatorType(pageComponent.getLocatorType());
            pageComponents.add(newComponent);
        }
        driver.log(Level.INFO, "Found %s elements by Locator: %s", pageComponents.size(), this.getByLocator());
        return pageComponents;
    }

    @Override
    public void click() {
        driver.log(Level.INFO, "Clicking on element by Locator: %s", this.getByLocator());
        if (this.hasWebElement()) {
            this.getWebElement().click();
        } else {
            new WebDriverWait(driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                    .until(ExpectedConditions.presenceOfElementLocated(this.getByLocator()))
                    .click();
        }
    }

    public void clickWithOffset(int xOffset, int yOffset) {
        driver.log(Level.INFO, "Clicking on element by Locator: %s with xOffset: %s and yOffset: %s", this.getByLocator(), xOffset, yOffset);
        this.waitUntilPresent();
        Actions actions = new Actions(driver.getDriver());
        actions.moveToElement(this.getWebElement(), xOffset, yOffset).click().build().perform();
    }

    public void clickWhilePresent() {
        driver.log(Level.INFO, "Clicking on element by Locator: %s while the element is still visible", this.getByLocator());
        int tries = 0;
        this.isPresent(60);
        while (this.isPresent(1)) {
            this.find().click();
            if (tries++ > 60) {
                throw new IOSTestException("Component by locator: %s is still displayed after 60 tries!", this.getByLocator());
            }
        }
        throw new IOSTestException("Component by locator: %s was not present after 60 seconds!", this.getByLocator());
    }

    public PageComponent waitUntilVisible() {
        driver.log(Level.INFO, "Waiting until element by Locator: %s is visible", this.getByLocator());
        new WebDriverWait(driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(this.getByLocator()));
        return this;
    }

    public PageComponent waitUntilPresent() {
        driver.log(Level.INFO, "Waiting until element by Locator: %s is present", this.getByLocator());
        new WebDriverWait(driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(this.getByLocator()));
        return this;
    }

    @Override
    public void submit() {
        driver.log(Level.INFO, "Submitting element value by Locator: %s", this.getByLocator());
        new WebDriverWait(driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(this.getByLocator()))
                .submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        driver.log(Level.INFO, "Sending keys to element by Locator: %s, value: %s", this.getByLocator(), Arrays.toString(keysToSend));
        new WebDriverWait(driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(this.getByLocator()))
                .sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        driver.log(Level.INFO, "Clearing element value by Locator: %s", this.getByLocator());
        new WebDriverWait(driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(this.getByLocator()))
                .clear();
    }

    public void verifyText(String expected) {
        driver.log(Level.INFO, "Verifying text of element by Locator: %s", this.getByLocator());
        String actual = this.waitUntilPresent().getText();
        driver.log(Level.INFO, "Comparing expected text: %s to actual text: %s", expected, actual);
        if (!actual.equals(expected)) {
            throw new IOSTestException("Expected text: %s does not equals to actual text: %s of a element by Locator: %s", expected, actual, this.getByLocator());
        }
    }

    public void verifyContainsText(String expected, boolean toLowerCase) {
        driver.log(Level.INFO, "Verifying if element by Locator: %s contains text: %s", this.getByLocator(), expected);
        String actual = this.waitUntilPresent().getText();
        if (toLowerCase) {
            actual = actual.toLowerCase();
            expected = expected.toLowerCase();
        }
        driver.log(Level.INFO, "Checking if actual text: %s contains expected text: %s", actual, expected);
        if (!actual.contains(expected)) {
            throw new IOSTestException("Actual text: %s does not contains expected text: %s for a element by Locator: %s", actual, expected, this.getByLocator());
        }
    }

    public void verifyContainsText(String expected) {
        this.verifyContainsText(expected, false);
    }

    @Override
    public String getTagName() {
        return this.getWebElement().getTagName();
    }

    @Override
    public String getDomProperty(String name) {
        return this.getWebElement().getDomProperty(name);
    }

    @Override
    public String getDomAttribute(String name) {
        return this.getWebElement().getDomAttribute(name);
    }

    @Override
    public String getAttribute(String name) {
        return this.getWebElement().getAttribute(name);
    }

    @Override
    public String getAriaRole() {
        return this.getWebElement().getAriaRole();
    }

    @Override
    public String getAccessibleName() {
        return this.getWebElement().getAccessibleName();
    }

    @Override
    public boolean isSelected() {
        return this.getWebElement().isSelected();
    }

    @Override
    public boolean isEnabled() {
        return this.getWebElement().isEnabled();
    }

    @Override
    public String getText() {
        return this.getWebElement().getText();
    }

    public String getNumbersFromText() {
        return this.getText().replaceAll("[^0-9]", "");
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.getRemoteWebDriver().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return driver.getRemoteWebDriver().findElement(by);
    }

    @Override
    public SearchContext getShadowRoot() {
        return this.getWebElement().getShadowRoot();
    }

    @Override
    public boolean isDisplayed() {
        driver.log(Level.INFO, "Checking if element by Locator: %s is displayed", this.getByLocator());
        return this.findElement(this.getByLocator()).isDisplayed();
    }

    public boolean isDisplayed(int seconds) {
        driver.log(Level.INFO, "Checking if element by Locator: %s is displayed, wait is %s seconds", this.getByLocator(), seconds);
        try {
            new WebDriverWait(driver.getDriver(), Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(this.getByLocator()));
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean isPresent(int seconds) {
        driver.log(Level.INFO, "Checking if element by Locator: %s is present, wait is %s seconds", this.getByLocator(), seconds);
        try {
            new WebDriverWait(driver.getDriver(), Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.presenceOfElementLocated(this.getByLocator()));
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    @Override
    public Point getLocation() {
        return this.getWebElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return this.getWebElement().getSize();
    }

    @Override
    public Rectangle getRect() {
        return this.getWebElement().getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return this.getWebElement().getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
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

    private WebElement getWebElement() {
        if (this.hasWebElement()) {
            return webElement;
        } else {
            return this.find();
        }

    }

    protected void setWebElement(WebElement webElement) {
        this.webElement = webElement;
    }

    private boolean hasWebElement() {
        return this.webElement != null;
    }
}