package demo.wrappers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wrappers {
    Logger logger = LogManager.getLogger(Wrappers.class);

    public boolean clickOnElement(WebElement element, WebDriver driver) {
        try {
            if (element != null && element.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                logger.info("Clicked on element successfully.");
                return true;
            } else {
                logger.warn("Element not found or not present");
                return false;
            }
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            logger.error("Error interacting with element: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while clicking: {}", e.getMessage());
            return false;
        }
    }

    public boolean enterText(WebElement element, String text, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
            logger.info("Text '{}' entered successfully.", text);
            return true;
        } catch (Exception e) {
            logger.error("Failed to enter text: {}", e.getMessage());
            return false;
        }
    }

    public boolean navigateToURL(String url, WebDriver driver) {
        try {
            String currentURL = driver.getCurrentUrl();
            if (!currentURL.equals(url)) {
                driver.get(url);
                logger.info("Navigated to new URL: {}", url);
                return true;
            } else {
                logger.info("Already on the desired URL: {}", url);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error during navigation: {}", e.getMessage());
            return false;
        }
    }

    public boolean waitForElementVisible(WebDriver driver, WebElement element, Duration time) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Element is visible.");
            return true;
        } catch (WebDriverException e) {
            logger.error("Error while waiting for element visibility: {}", e.getMessage());
            return false;
        }
    }

    public boolean selectCheckboxByLabel(String label, WebDriver driver) {
        try {
            WebElement checkbox = driver.findElement(By.xpath("//span[text()='" + label + "']"));
            return clickOnElement(checkbox, driver);
        } catch (Exception e) {
            logger.error("Checkbox not found for label '{}': {}", label, e.getMessage());
            return false;
        }
    }

    public boolean selectDropdownOption(String visibleText, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='"+visibleText+"'])[2]")));
            return clickOnElement(option, driver);
        } catch (Exception e) {
            logger.error("Dropdown option not found '{}': {}", visibleText, e.getMessage());
            return false;
        }
    }
}
