package sazka.ios.test.components;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sazka.ios.test.base.PageComponent;
import sazka.ios.test.base.driver.Driver;

import java.time.Duration;

public class RandomComponent extends PageComponent {
    public RandomComponent(Driver driver) {
        super(driver);
    }

    public boolean verifyMyText(String text) {
        String actualText = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(this.getLocator())))
                .getText();
        return actualText.equals(text);
    }
}
