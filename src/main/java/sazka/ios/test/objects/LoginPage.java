package sazka.ios.test.objects;

import sazka.ios.test.base.PageObject;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.components.AnyComponent;
import sazka.ios.test.data.User;

public class LoginPage extends PageObject {

    @Locator(value = "E-mailová adresa")
    private AnyComponent emailInput;

    @Locator(value = "Heslo")
    private AnyComponent passwordInput;

    @Locator(value = "PŘIHLÁSIT")
    private AnyComponent loginButton;

    @Locator(value = "DĚKUJI, TEĎ NE")
    private AnyComponent declineButton;

    @Locator(value = "//*[@name=\"Teď ne\" or @name=\"Not Now\"]", type = Locator.LocatorType.XPATH)
    private AnyComponent declineSavePassword;

    @Locator(value = "closeAfter")
    private AnyComponent closeBonusButton;

    @Locator(value = "closeChat")
    private AnyComponent closeScoreButton;

    public LoginPage(Driver driver) {
        super(driver);
    }

    public HomePage login(User user) {
        emailInput.clear();
        emailInput.sendKeys(user.getUsername());
        passwordInput.clear();
        passwordInput.sendKeys(user.getPassword());
        loginButton.click();
        driver.sleep(5000);
        if (declineSavePassword.isDisplayed(3)) {
            declineSavePassword.click();
        }
        if (closeBonusButton.isDisplayed(3)) {
            closeBonusButton.click();
        }
        if (closeScoreButton.isDisplayed(3)) {
            closeScoreButton.click();
        }
        return new HomePage(driver);
    }
}
