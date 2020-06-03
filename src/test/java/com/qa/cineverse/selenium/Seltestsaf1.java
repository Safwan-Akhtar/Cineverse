package com.qa.cineverse.selenium;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import java.io.File;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Seltestsaf1 {



    @LocalServerPort
    private int port;
    private WebDriver driver;
    private ExtentReports report;
    ExtentTest test;

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions opts = new ChromeOptions();
        this.driver = new ChromeDriver();

        report = new ExtentReports(
                System.getProperty("user.dir") + "/test-output/Report.html",
                false
        );
        report
                .addSystemInfo("Host Name", "QA")
                .addSystemInfo("Tester", "Saf");
        report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-report.xml"));
    }

    @Test
    public void test1() throws InterruptedException {
        test = report.startTest("Verifying the title of Cineverse website");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigating to the Cineverse website");

        WebElement header = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("header")));
        assertTrue(header.isDisplayed());
        test.log(LogStatus.PASS, "The title was exactly the same");

    }

    @Test
    public void indexBanner() throws InterruptedException {
        test = report.startTest("Verifying the Banner of index page");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigating to the Cineverse website");


        WebElement bannerButtonNext = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("carouselNextButton")));
        bannerButtonNext.click();
        test.log(LogStatus.PASS, "the Dynamic carousel next button works");
        WebElement bannerButtonPrevious = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("carouselPreviousButton")));
        bannerButtonPrevious.click();
        test.log(LogStatus.PASS, "the Dynamic carousel previous button works");
        WebElement banner = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("myCarousel")));
        assertTrue(banner.isDisplayed());
        test.log(LogStatus.PASS, "the Dynamic carousel is present and both buttons work");
    }

    @Test
    public void mealDeals() {
        test = report.startTest("Verifying the Meal Combo Deals of index page");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigating to the Cineverse website");

        WebElement comboMeal1 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("deal1")));
        assertTrue(comboMeal1.isDisplayed());
        test.log(LogStatus.PASS, "The card for meal 1 is present");
        WebElement comboMeal2 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("deal2")));
        assertTrue(comboMeal2.isDisplayed());
        test.log(LogStatus.PASS, "The card for meal 2 is present");
        WebElement comboMeal3 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("deal3")));
        assertTrue(comboMeal3.isDisplayed());
        test.log(LogStatus.PASS, "The card for meal 3 is present");
        WebElement comboMeal4 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("deal4")));
        assertTrue(comboMeal4.isDisplayed());
        test.log(LogStatus.PASS, "The card for meal 4 is present");
    }

    @Test
    public void moviePosters(){
        test = report.startTest("Verifying the recommended movie posters of index page");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigating to the Cineverse website");

        WebElement poster1 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("poster1")));
        assertTrue(poster1.isDisplayed());
        test.log(LogStatus.PASS, "The card for poster 1 is present");
        WebElement poster2 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("poster2")));
        assertTrue(poster2.isDisplayed());
        test.log(LogStatus.PASS, "The card for poster 2 is present");
        WebElement poster3 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("poster3")));
        assertTrue(poster3.isDisplayed());
        test.log(LogStatus.PASS, "The card for poster 3 is present");
        WebElement poster4 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("poster4")));
        assertTrue(poster4.isDisplayed());
        test.log(LogStatus.PASS, "The card for poster 4 is present");
    }

    @Test
    public void screenNav() {
        test = report.startTest("Verifying navigation to Screens page and drop down buttons are working");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigating to the Cineverse website");

        WebElement infoDrop = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("infoDrop")));
        infoDrop.click();
        test.log(LogStatus.PASS, "navigation drop down works");
        WebElement screens = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("screensNavButton")));
        screens.click();
        test.log(LogStatus.PASS, "link button to Screens page works");
        WebElement screensTitle = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("screensTitle")));
        assertTrue(screensTitle.isDisplayed());
        test.log(LogStatus.PASS, "successfully navigated to screens page");
        WebElement standardDrop = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("standardDrop")));
        standardDrop.click();
        test.log(LogStatus.PASS, "standard seats screen plan drop down works");
        WebElement deluxeDrop = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("deluxeDrop")));
        deluxeDrop.click();
        test.log(LogStatus.PASS, "deluxe seats screen plan drop down works");
    }

    @Test
    public void openingTimesNav() {
        test = report.startTest("Verifying navigation to Opening times page is working");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigating to the Cineverse website");

        WebElement infoDrop = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("infoDrop")));
        infoDrop.click();
        test.log(LogStatus.PASS, "navigation drop down works");
        WebElement openTimes = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("openingTimesNavButton")));
        openTimes.click();
        test.log(LogStatus.PASS, "navigation link to opening times works");
    }

    @AfterMethod
    public void getResult(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
            test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
        }else if(result.getStatus() == ITestResult.SUCCESS){
            test.log(LogStatus.PASS, "Test Case Skipped is "+result.getName());
        }
        report.endTest(test);
    }

    @After
    public void teardown(){
        report.flush();
        report.close();
        this.driver.quit();
    }

}
