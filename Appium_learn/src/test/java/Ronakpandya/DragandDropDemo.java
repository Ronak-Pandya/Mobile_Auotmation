package Ronakpandya;

import com.beust.ah.A;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.SecureRandom;

public class DragandDropDemo extends BaseTest {

    @Test
    public void DragandDropDemoTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement source = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
        DragAndDropAction(source, 830, 800);
        String result = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
        System.out.println(result);
        Assert.assertEquals(result, "Dropped!");


//        scrolltoEndAction();

    }
}
