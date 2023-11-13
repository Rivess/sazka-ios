package sazka.ios.test.objects;

import sazka.ios.test.base.PageObject;
import sazka.ios.test.base.annotations.Locator;
import sazka.ios.test.base.driver.Driver;
import sazka.ios.test.components.AnyComponent;
import sazka.ios.test.objects.lotteries.eurojackpot.EurojackpotPage;
import sazka.ios.test.objects.lotteries.sportka.SportkaPage;

public class HomePage extends PageObject {

    @Locator(value = "unlogged user", type = Locator.LocatorType.ID)
    private AnyComponent loginButton;

    @Locator(value = "id_Sportka")
    private AnyComponent sportkaTile;

    @Locator(value = "id_Eurojackpot")
    private AnyComponent eurojackpotTile;

    @Locator(value = "id_balanceLabel")
    private AnyComponent balance;

    @Locator(value = "sidemenu hamburger")
    private AnyComponent hamburgerMenu;

    public HomePage(Driver driver) {
        super(driver);
    }

    public LoginPage clickOnLogin() {
        loginButton.click();
        return new LoginPage(driver);
    }

    public SportkaPage clickOnSportka() {
        sportkaTile.click();
        return new SportkaPage(driver);
    }

    public EurojackpotPage clickOnEurojackpot() {
        eurojackpotTile.click();
        return new EurojackpotPage(driver);
    }

    public MyAccountPage openMyAccount() {
        balance.click();
        return new MyAccountPage(driver);
    }

    public HamuburgerMenuPage openHamburgerMenu() {
        hamburgerMenu.click();
        return new HamuburgerMenuPage(driver);
    }

    public void resetUser() {
        if (balance.isDisplayed(3)) {
            this.openMyAccount().logoutUser();
        }
        this.openHamburgerMenu().home();
    }
}
