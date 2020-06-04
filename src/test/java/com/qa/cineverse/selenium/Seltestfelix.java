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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;
import static java.lang.Thread.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Seltestfelix {

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
    }

    @Ignore
    @Test
    public void navToGallarySearch() throws InterruptedException, IOException {
        test = report.startTest("Search page tests");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen.");

        driver.get("http://localhost:" + port);
        test.log(LogStatus.INFO, "Navigated to the Cineverse website.");
        sleep(2000);

        // add id once searchGal has been added to navbar
        driver.findElement(By.id("")).click();
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
        sleep(2000);

        driver.findElement(By.id("tt0073195")).click();
        test.log(LogStatus.INFO, "Clicked 'movie details'.");
        sleep(2000);
        driver.findElement(By.id("movieDetails")).isDisplayed();
        test.log(LogStatus.INFO, "Validated movie details page.");
        sleep(2000);
        File scrFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile2, new File("test-output" + File.separator + "searchInfoScreenshot.png"));
        sleep(2000);

    }

    @Ignore
    @Test
    public void listingsGallary() throws InterruptedException, IOException{
        test = report.startTest("Verifying the title of Cineverse website");
        driver.manage().window().maximize();
        test.log(LogStatus.INFO, "Started chrome browser and made it fullscreen");

        driver.findElement(By.id("whatOnDrop")).click();
        test.log(LogStatus.INFO, "Clicked what's on drop down.");
        sleep(2000);
        driver.findElement(By.id("listingGallaryNavButton")).click();
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







//// choose random option from drop downs - alternatively use loop?
//    @Test
//    public void randomDropDown() throws InterruptedException {
//
//    test = report.startTest("Verifying the title of Cineverse website");
//    driver.manage().window().maximize();
//
//    // Locate the dropdown menu
//    WebElement dropdown = (WebElement) driver.findElements(By.id("infoDrop"));
//    dropdown.click();
//
//    //Get the list of dropdown options
//    List<WebElement> itemsInDropdown = driver.findElements(By.id("id of the dropdown list"));
//
//    // Get the size of dropdown list
//    int size = itemsInDropdown.size();
//
//    // Generate the random number
//    int randomNumber = ThreadLocalRandom.current().nextInt(0, size);
//
//    // Clicking on random value
//    itemsInDropdown.get(randomNumber).click();
//}