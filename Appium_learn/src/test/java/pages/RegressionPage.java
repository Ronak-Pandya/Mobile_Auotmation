package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegressionPage {
    private final AppiumDriver driver;
    private final WebDriverWait wait;

    // TODO: Replace with your real app locators
    private final By regressionPageTitle = AppiumBy.accessibilityId("regression_page_title");
    private final By runRegressionButton = AppiumBy.accessibilityId("run_regression_button");
    private final By successMessage = AppiumBy.accessibilityId("regression_success_message");

    public RegressionPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(regressionPageTitle)).isDisplayed();
    }

    public void runRegression() {
        WebElement runBtn = wait.until(ExpectedConditions.elementToBeClickable(runRegressionButton));
        runBtn.click();
    }

    public boolean isSuccessVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }
}

