package com.qa.cineverse.selenium;

import com.qa.cineverse.App;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class Seltestfel {
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;

    @BeforeTest
    public void startReport(){
        report = new ExtentReports(
                System.getProperty("user.dir") + "/test-output/Report.html",
                true
        );
        report
                .addSystemInfo("Host Name", "QA")
                .addSystemInfo("Tester", "Caroline");
        report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-report.xml"));
    }

    @BeforeMethod
    public void setUp(){
        SpringApplication.run(App.class);
        driver = new ChromeDriver();
    }

    public void openChrome() {
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:8180/");
        test.log(LogStatus.INFO, "Navigating to the application web interface");
    }

    @Test
    public void clickClassPage() throws InterruptedException, IOException {
        test = report.startTest("Verifying Navigation to Create User Page");
        navigateToWebInterface();
        sleep(5000);
        File webAppPic = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(webAppPic, new File(System.getProperty("user.dir") + "/test-output/webAppHome.jpg"));
        test.log(LogStatus.INFO, "Navigated to the web interface", "<img src=webAppHome.jpg>");
        sleep(2000);
        WebElement createUserLink = driver.findElement(By.id("userCreation"));
        createUserLink.click();
        test.log(LogStatus.INFO, "Click on userCreation link");
        sleep(2000);
        WebElement pageHeader = driver.findElement(By.id("pageHeader"));
        assertEquals(pageHeader.getText(), "GameTimeLog - Create a User");
        File userCreationPic = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

   }

   @AfterMethod
    public void getResults(ITestResult result){
        driver.close();
        if(result.getStatus() == ITestResult.FAILURE){
            test.log(LogStation.FAIl, "Test has failed" + result.getName());
            test.log(LogStation.FAIL, "Test has failed" + results.getThrowable());
        } else if (result.getStatus() == ITest.Result.SUCCESS) {
            test.log(LogStatus.PASS, "Test has passed" + result.getName());
        }
        Report.endTest(test);
   }
   @AfterTest
    public void endReport(){
        report.flush();
        report.close();
   }

}