package com.qa.cineverse.selenium;

import org.apache.commons.io.FileUtils;
import org.junit.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;
import static java.lang.Thread.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class allTests {

    @LocalServerPort
    private int port;
    private static ExtentReports report;
    private WebDriver driver;
    static ExtentTest test;

    @BeforeClass
    public static void beforeClass(){
        report = new ExtentReports("test-output" + File.separator + "Report.html", true);
        report
                .addSystemInfo("Host Name", "QA")
                .addSystemInfo("Tester", "Felix");
        report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-report.xml"));
    }

    @Before
    public void init() {
        System.setProperty("webdriver.home.driver", "chromedriver");
        ChromeOptions opts = new ChromeOptions();
        //        opts.setHeadless(true);
        this.driver = new ChromeDriver(opts);
    }

    @Test
    public void accessIndex() throws InterruptedException, IOException{
        test = report.startTest("Verifying the title of Cineverse website");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");

        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigated to the Cineverse website");

        WebElement header = driver.findElement(By.id("header"));
        assertTrue(header.isDisplayed());
        test.log(LogStatus.PASS, "The title was exactly the same");
        sleep(2000);

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("test-output"  + File.separator + "classificationScreenshot.png"));
        test.log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture("classificationScreenshot.png"));
    }

    @Test
    public void navToClassPage() throws InterruptedException, IOException {
        test = report.startTest("Classification page tests");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen.");

        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigated to the Cineverse website.");
        sleep(2000);

        driver.findElement(By.id("infoDrop")).click();
        test.log(LogStatus.INFO, "Info button clicked.");
        sleep(2000);
        driver.findElement(By.id("classificationNavButton")).click();
        test.log(LogStatus.INFO, "Classification page clicked.");
        sleep(2000);
        driver.findElement(By.id("uClick")).isDisplayed();
        test.log(LogStatus.INFO, "Unique element on Classification page exists, meaning the classification page was accessed.");
        sleep(2000);

        driver.findElement(By.id("uClick")).click();
        test.log(LogStatus.INFO, "click U button.");
        sleep(2000);
        driver.findElement(By.id("uDesc")).isDisplayed();
        test.log(LogStatus.INFO, "U description was displayed.");
        sleep(2000);

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("test-output"  + File.separator + "classificationScreenshot.png"));
        test.log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture("classificationScreenshot.png"));
    }



    @Test
    public void navToComingHere() throws InterruptedException, IOException {
        test = report.startTest("Getting here page tests");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen.");

        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigated to the Cineverse website.");
        sleep(2000);

        driver.findElement(By.id("comingHereNavButton")).click();
        test.log(LogStatus.INFO, "Getting here clicked.");
        sleep(2000);
        driver.findElement(By.id("gettingThere")).isDisplayed();
        test.log(LogStatus.INFO, "Unique element from the 'getting here' page exists, meaning the classification page was accessed.");
        sleep(2000);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("test-output"  + File.separator + "comingHereScreenshot.png"));
        test.log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture("comingHereScreenshot.png"));
    }

    @Test
    public void navToGallarySearch() throws InterruptedException, IOException {
        test = report.startTest("Search page tests");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen.");

        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigated to the Cineverse website.");
        sleep(2000);

        driver.findElement(By.id("whatOnDrop")).click();
        test.log(LogStatus.INFO, "Clicked what's on drop down.");
        sleep(2000);
        driver.findElement(By.id("gallerySearchNavButton")).click();
        test.log(LogStatus.INFO, "Search gallary button clicked.");
        sleep(2000);
        driver.findElement(By.id("searchForm")).isDisplayed();
        test.log(LogStatus.INFO, "Unique element from the search gallary page exists, meaning the search gallary page was accessed.");
        sleep(2000);

        driver.findElement(By.id("searchText")).click();
        test.log(LogStatus.INFO, "Clicked search bar.");
        sleep(2000);
        driver.findElement(By.id("searchText")).sendKeys("jaws" + Keys.ENTER);
        test.log(LogStatus.INFO, "Entered 'jaws' into search.");
        sleep(2000);
        driver.findElement(By.id("tt0073195-img")).isDisplayed();
        test.log(LogStatus.INFO, "Unique element on the 'getting here' page exists, meaning the classification page was accessed.");
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("test-output" + File.separator + "searchScreenshot.png"));
        test.log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture("searchScreenshot.png"));
        sleep(2000);

        driver.findElement(By.id("tt0073195")).click();
        test.log(LogStatus.INFO, "Clicked 'movie details'.");
        sleep(2000);
        driver.findElement(By.id("MovieDetails")).isDisplayed();
        test.log(LogStatus.INFO, "Validated movie details page.");
        sleep(2000);
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2, new File("test-output" + File.separator + "searchInfoScreenshot.png"));
        test.log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture("searchInfoScreenshot.png"));
        sleep(2000);

        test.log(LogStatus.PASS,"Test passed");

    }

    @Test
    public void listingsGallary() throws InterruptedException, IOException{
        test = report.startTest("Search page tests");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen.");

        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigated to the Cineverse website.");
        sleep(2000);

        driver.findElement(By.id("whatOnDrop")).click();
        test.log(LogStatus.INFO, "Clicked what's on drop down.");
        sleep(2000);
        driver.findElement(By.id("listingGalleryNavButton")).click();
        test.log(LogStatus.INFO, "Current listings page clicked.");
        sleep(2000);
        driver.findElement(By.id("currentListings")).isDisplayed();
        test.log(LogStatus.INFO, "Unique element on current listings page exists, meaning the classification page was accessed.");
        sleep(2000);

        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigated to the Cineverse website");

        WebElement header = driver.findElement(By.id("header"));
        assertTrue(header.isDisplayed());
        test.log(LogStatus.PASS, "The title was exactly the same");
        sleep(2000);

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("test-output" + File.separator + "indexScreenshot.png"));
        test.log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture("indexScreenshot.png"));
    }

    // Saf tests
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

    @Test
    public void upcomingNav () throws InterruptedException {
        test = report.startTest("Verifying navigation to upcoming releases page is working");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");
        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigating to the Cineverse website");

        WebElement whatsOnDrop = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("whatOnDrop")));
        whatsOnDrop.click();
        test.log(LogStatus.PASS, "navigation drop down for whats on works");
        WebElement upComingNav = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("upcomingReleasesNavButton")));
        upComingNav.click();
        test.log(LogStatus.PASS, "navigation link to up coming releases works");
    }

    @AfterClass
    public static void endReport(){
        report.flush();
        report.close();
    }

    @After
    public void tearDown() {
        this.driver.quit();
        report.endTest(test);
    }

}