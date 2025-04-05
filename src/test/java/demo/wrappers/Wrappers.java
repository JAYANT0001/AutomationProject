package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    public boolean clickOnElement(WebElement element, WebDriver driver) {
        try {
            if (element != null && element.isDisplayed()) {

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true)", element);
                element.click();
                return true;
            } else {
                System.out.println("Element not found or not present");
                return false;
            }

        } catch (NoSuchElementException e) {
            System.out.println("Element not found" + e.getMessage());
            return false;
        } catch (ElementNotInteractableException e) {
            System.out.println("Element not interactable" + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error occured while clicking on element" + e.getMessage());
            return false;
        }
    }

    public boolean enterText(WebElement element, String text, WebDriver driver) {
        if (element != null && element.isDisplayed()) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(text);
            System.out.println("Text entered sucessfully");
            return true;
        } else {
            System.out.println("Element not found or not visible");
            return false;
        }
    }

    public boolean navigateToURL(String url, WebDriver driver) {

        try {
            String currentURL = driver.getCurrentUrl();

            if (!currentURL.equals(url)) {
                driver.get(url);
                System.out.println("Navigated to new URL" + url);
                return true;
            } else {
                System.out.println("The given URL is same as present URL, no navigation needed");
                return false;
            }

        } catch (Exception e) {
            System.out.println("An error occured during the navigation" + e.getMessage());
            return false;
        }

    }

    public boolean waitForElementVisible(WebDriver driver, WebElement element, Duration time) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;

        } catch (WebDriverException e) {
            System.out.println("An error occured while waiting for the element");
            return false;
        }

    }

}
