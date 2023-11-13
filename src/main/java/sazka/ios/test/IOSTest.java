package sazka.ios.test;

import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.annotations.*;
import sazka.ios.test.base.driver.IOS;
import sazka.ios.test.base.listeners.TestFailureListener;
import sazka.ios.test.data.lotteries.Lottery;
import sazka.ios.test.objects.HomePage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Listeners(value = {TestFailureListener.class})
public class IOSTest implements ITestListener {
    protected IOS driver;

    @BeforeSuite
    public void setUp(ITestContext context) {
        this.setSetupLogFileName();
        driver = new IOS();
        driver.activateApp();
        context.setAttribute("driver", driver);
    }

    @AfterSuite
    public void resetApp() {
        if (driver != null) {
            driver.reset();
        }
    }

    @AfterMethod
    public void cleanUpApp(ITestContext context) {
        if (driver != null) {
            driver.reset();
        }
        this.setCleanUpLogFileName();
        driver = new IOS();
        context.setAttribute("driver", driver);
        driver.activateApp();
        HomePage homePage = new HomePage(driver);
        homePage.resetUser();
    }

    public void setSetupLogFileName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        String folderName = "log/" + formatter.format(now) + "-" + this.getClass().getSimpleName();
        ThreadContext.put("logFileName", folderName + "/setUp.log");
        ThreadContext.put("logFolderName", folderName);
        ThreadContext.put("screenshotFolderName", folderName);
    }

    @BeforeMethod
    public void setTestLogFileName(Object[] data) {
        String testName;
        if (data.length > 0 && data[0] instanceof Lottery) {
            testName = "/" + ((Lottery) data[0]).getTestName();
        } else {
            testName = "";
        }
        String folderName = ThreadContext.get("logFolderName") + testName;
        ThreadContext.put("logFileName", folderName + "/test.log");
        ThreadContext.put("screenshotFolderName", folderName);
    }

    private void setCleanUpLogFileName() {
        String logFileName = ThreadContext.get("logFolderName") + "/cleanUpApp.log";
        ThreadContext.put("logFileName", logFileName);
        ThreadContext.put("screenshotFolderName", ThreadContext.get("logFolderName"));
    }
}
