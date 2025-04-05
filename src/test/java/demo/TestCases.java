package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Calendar;
import java.util.Date;

import demo.wrappers.Wrappers;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    Wrappers wrappers = new Wrappers();
    String Url = "https://forms.gle/wjPkzeSEk1CM7KgGA";
    Long epochTime = Instant.now().getEpochSecond();
    String name = "Crio Learner";
    String msg = "I want to be the best QA Engineer!" + epochTime;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */
    @Test
    public void testCase01() throws InterruptedException {
        wrappers.navigateToURL(Url, driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nameTextBox = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='Xb9hP']/input)[1]")));
        wrappers.enterText(nameTextBox, name, driver);
        WebElement whyTextBox = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='Pc9Gce Wic03c']/textarea")));
        wrappers.enterText(whyTextBox, msg, driver);
        WebElement experienceRadio = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='vd3tt'])[2]")));
        if (!experienceRadio.isSelected()) {
            experienceRadio.click();
        }
        if (experienceRadio.isSelected()) {
            System.out.println("Radio button is selected");
        } else {
            System.out.println("Radio button is selected");
        }
        WebElement javaElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Java']")));
        WebElement seleniumElement = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Selenium']")));
        WebElement testNGElement = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='TestNG']")));
        wrappers.clickOnElement(javaElement, driver);
        wrappers.clickOnElement(seleniumElement, driver);
        wrappers.clickOnElement(testNGElement, driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='e2CuFe eU809d'])[1]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Mr'])[2]"))).click();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date dateSevenDaysBefore = calendar.getTime();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateformat.format(dateSevenDaysBefore);
        WebElement datePicker = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='date']")));
        wrappers.enterText(datePicker, formattedDate, driver);
        WebElement timeHourField = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@jsname='YPqjbf'])[3]")));
        WebElement timesecondField = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@jsname='YPqjbf'])[4]")));
        wrappers.enterText(timeHourField, "07", driver);
        wrappers.enterText(timesecondField, "30", driver);
        WebElement submitButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Submit']")));
        submitButton.click();
        WebElement successElement = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']")));
        String successMessage = successElement.getText();
        System.out.println(successMessage);

    }

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}