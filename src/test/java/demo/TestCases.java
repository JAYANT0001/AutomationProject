package demo;

import demo.wrappers.Wrappers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

public class TestCases {
        ChromeDriver driver;
        Logger logger = LogManager.getLogger(TestCases.class);

        @Test
        public void testCase01() throws InterruptedException {
                logger.info("========== Start Test case: testCase01 ==========");

                Wrappers wrapper = new Wrappers();
                String formUrl = "https://forms.gle/wjPkzeSEk1CM7KgGA";

                // Step 1: Navigate to Form
                wrapper.navigateToURL(formUrl, driver);
                Thread.sleep(2000);

                // Step 2: Enter Name
                WebElement nameField = new WebDriverWait(driver, Duration.ofSeconds(10))
                                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                                "(//div[contains(text(),'Your answer')])[1]//preceding-sibling::input")));
                wrapper.enterText(nameField, "Crio Learner", driver);

                // Step 3: Enter Reason with Epoch
                String reasonText = "I want to be the best QA Engineer! " + Instant.now().getEpochSecond();
                WebElement reasonField = driver
                                .findElement(By.xpath("//div[@class='Pc9Gce Wic03c']/textarea"));
                wrapper.enterText(reasonField, reasonText, driver);

                // Step 4: Select Automation Experience (Radio)
                WebElement experienceRadio = driver
                                .findElement(By.xpath("(//div[@class='vd3tt'])[2]"));
                wrapper.clickOnElement(experienceRadio, driver);

                // Step 5: Select Tech Skills (Checkboxes)
                String[] tools = { "Java", "Selenium", "TestNG" };
                for (String tool : tools) {
                        wrapper.selectCheckboxByLabel(tool, driver);
                }

                // Step 6: Select Title from Dropdown
                WebElement dropdown = driver.findElement(By.xpath("(//div[@class='e2CuFe eU809d'])[1]"));
                wrapper.clickOnElement(dropdown, driver);
                wrapper.selectDropdownOption("Mr", driver);
                Thread.sleep(3000);

                // Step 7: Enter Date (7 Days Ago)
                String dateStr = LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                WebElement dateField = driver.findElement(By.xpath("//input[@type='date']"));
                wrapper.enterText(dateField, dateStr, driver);

                // Step 8: Enter Time
                WebElement timeHourField = driver.findElement(By.xpath("(//input[@jsname='YPqjbf'])[3]"));
                WebElement timesecondField = driver.findElement(By.xpath("(//input[@jsname='YPqjbf'])[4]"));
                wrapper.enterText(timeHourField, "07", driver);
                wrapper.enterText(timesecondField, "30", driver);

                // Step 9: Submit the Form
                WebElement submitBtn = driver
                                .findElement(By.xpath("//span[text()='Submit']"));
                wrapper.clickOnElement(submitBtn, driver);

                // Step 10: Verify Submission Message
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement confirmationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//div[text()='Thanks for your response, Automation Wizard!']")));
                logger.info("Submission successful: {}", confirmationMsg.getText());

                logger.info("========== End Test case: testCase01 ==========");
        }

        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                logger.info("Browser started and window maximized");
        }

        @AfterTest
        public void endTest() {
                if (driver != null) {
                        driver.close();
                        driver.quit();
                        logger.info("Browser closed successfully");
                }
        }
}
