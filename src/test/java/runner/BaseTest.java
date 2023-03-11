package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.lang.reflect.Method;
//import pages.start.StartPage;

public abstract class BaseTest {

    private WebDriver driver;
    private WebDriverWait webDriverWait20;
    private WebDriverWait webDriverWait10;
    private WebDriverWait webDriverWait15;


    @BeforeSuite
    protected void beforeSuite() {
        Reporter.log(ReportUtils.getReportHeader(), true);
    }

    private final String BASE_URL = "http://www.99-bottles-of-beer.net";

    @BeforeMethod
    protected void beforeMethod(ITestResult result) {
        driver = BaseUtils.createDriver();
        Reporter.log(ReportUtils.END_LINE, true);
        Reporter.log("TEST RUN", true);
        Reporter.log(ReportUtils.getClassName(result), true);
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult result) {
        Reporter.log(ReportUtils.getTestStatistics(method, result), true);
        Reporter.log("Test finished", true);

        driver.quit();
        webDriverWait20 = null;
        webDriverWait10 = null;
        webDriverWait15 = null;


    }

    protected WebDriver getDriver() {
        return driver;
    }
//
//    public StartPage openBaseURL() {
//        getDriver().get(BASE_URL);
//
//        return new StartPage(getDriver());
//    }
//
//    public String getExternalPageTitle() {
//
//        return getDriver().getTitle();
//    }
//
//    public String getExternalPageURL() {
//
//        return getDriver().getCurrentUrl();
//    }
}
