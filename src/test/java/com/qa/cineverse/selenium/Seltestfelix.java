package com.qa.cineverse.selenium;

import static java.lang.Thread.*;

import com.qa.cineverse.App;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Seltestfelix {

    @LocalServerPort
    private int port;
    private ExtentReports report;
    private WebDriver driver;
    ExtentTest test;

    @Before
    public void init() {
        System.setProperty("webdriver.home.driver", "chromedriver");
        ChromeOptions opts = new ChromeOptions();
        this.driver = new ChromeDriver();

        report = new ExtentReports(
                System.getProperty("user.dir") + "/test-output/Report.html",
                true
        );
        report
                .addSystemInfo("Host Name", "QA")
                .addSystemInfo("Tester", "Felix");
        report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-report.xml"));
    }

    @Test
    public void test1() throws InterruptedException {
        test = report.startTest("Verifying the title of Cineverse website");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigating to the Cineverse website");
        WebElement header = driver.findElement(By.id("header"));
        assertTrue(header.isDisplayed());
        test.log(LogStatus.PASS, "The title was exactly the same");
        sleep(4000);

    }

    @After
    public void tearDown() {
        this.driver.quit();
    }
}

//        WebElement name = driver.findElement(By.id("createName"));
//        name.sendKeys("Donald");
//        WebElement colour = driver.findElement(By.id("createColour"));
//        colour.sendKeys("White");
//        WebElement habitat = driver.findElement(By.id("createHabitat"));
//        habitat.sendKeys("Donald");
//
//        WebElement button = driver.findElement(By.id("createButton"));
//        button.click();
//
//        WebElement output = driver.findElement(By.id("createOutput"));
//
//        assertFalse(output.getText().isBlank());
//    }
//
//

//
//}
//
//
//
//
//
//
//
//    @BeforeTest
//    public void startReport(){
//        report = new ExtentReports(
//                System.getProperty("user.dir") + "/test-output/Report.html",
//                true
//        );
//        report
//                .addSystemInfo("Host Name", "QA")
//                .addSystemInfo("Tester", "Felix");
//        report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-report.xml"));
//    }
//
//    @BeforeMethod
//    public void setUp(){
//        SpringApplication.run(App.class);
//        System.setProperty("webdriver.chrome.driver", "chromedriver");
//        driver = new ChromeDriver();
//    }
//
//    public void openChrome() {
//        driver.manage().window().maximize();
//        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
//        driver.get("http://localhost:8182/");
//        test.log(LogStatus.INFO, "Navigating to the application web interface");
//    }
//
//    @Test
//    public void seleniumDrawHouse() throws InterruptedException{
//        driver.manage().window().maximize();
//        sleep( 2000);
//        driver.get("https://www.youidraw.com/apps/painter/");
//        sleep ( 2000);
//
//        Actions action = new Actions(driver);
//        sleep( 2000);
//
//
////    @Test
////    public void clickClassPage() throws InterruptedException, IOException {
////        test = report.startTest("Verifying Navigation to Create User Page");
////        openChrome();
////        sleep(5000);
////        File webAppPic = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
////        FileUtils.copyFile(webAppPic, new File(System.getProperty("user.dir") + "/test-output/webAppHome.jpg"));
////        test.log(LogStatus.INFO, "Navigated to the web interface", "<img src=webAppHome.jpg>");;
////        WebElement pageHeader = driver.findElement(By.id("header"));
////        assertEquals(pageHeader.getText(), "Welcome to Cineverse");
//
//
//
//
////        WebElement createUserLink = driver.findElement(By.id("userCreation"));
//
////        FileUtils.copyFile(webAppPic, new File(System.getProperty("user.dir") + "/test-output/webAppHome.jpg"));
////        test.log(LogStatus.INFO, "Navigated to the web interface", "<img src=webAppHome.jpg>");
////        sleep(2000);
////        WebElement createUserLink = driver.findElement(By.id("userCreation"));
////        createUserLink.click();
////        test.log(LogStatus.INFO, "Click on userCreation link");
////        sleep(2000);
////        WebElement pageHeader = driver.findElement(By.id("pageHeader"));
////        assertEquals(pageHeader.getText(), "GameTimeLog - Create a User");
////        File userCreationPic = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//   }
//
//   @AfterMethod
//    public void getResults(ITestResult result){
//        driver.close();
//        if(result.getStatus() == ITestResult.FAILURE){
//            test.log(LogStatus.FAIL, "Test has failed" + result.getName());
//            test.log(LogStatus.FAIL, "Test has failed" + result.getThrowable());
//        } else if (result.getStatus() == ITestResult.SUCCESS) {
//            test.log(LogStatus.PASS, "Test has passed" + result.getName());
//        }
//        report.endTest(test);
//   }
//   @AfterTest
//    public void endReport(){
//        report.flush();
//        report.close();
//   }
//
//}