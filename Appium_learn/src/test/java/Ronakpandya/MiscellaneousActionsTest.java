package Ronakpandya;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Map;

public class MiscellaneousActionsTest extends BaseTest {

    @Test
    public void rotate() {

        Activity activity = new Activity(
                "io.appium.android.apis",
                "io.appium.android.apis.preference.PreferenceDependencies"
        );

        driver.executeScript("mobile: startActivity", Map.of(
                "intent", activity.getAppPackage() + "/" + activity.getAppActivity()
        ));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(By.id("android:id/checkbox"))).click();

        driver.rotate(new DeviceRotation(0, 0, 90));

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"WiFi settings\")"
        )).click();

        String alertText = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertText, "WiFi settings");

        driver.setClipboardText("Ronak's WIFI");
        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());

        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.findElement(By.id("android:id/button1")).click();

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }
}
