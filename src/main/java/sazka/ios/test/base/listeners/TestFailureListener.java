package sazka.ios.test.base.listeners;

import org.apache.logging.log4j.Level;
import org.testng.ITestListener;
import org.testng.ITestResult;
import sazka.ios.test.base.driver.Driver;

public class TestFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Driver driver = (Driver) result.getTestContext().getAttribute("driver");
        if (driver != null) {
            driver.log(Level.ERROR, "There was an exception in test: ", result.getThrowable());
            driver.saveScreenShot();
            driver.saveSource();
        }
    }
}
