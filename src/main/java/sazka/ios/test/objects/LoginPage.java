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

    public LoginPage(Driver driver) {
        super(driver);
    }

    public HomePage login(User user) {
        this.emailInput.sendKeys(user.getUsername());
        this.passwordInput.sendKeys(user.getPassword());
        this.loginButton.click();
        return new HomePage(driver);
    }
}
