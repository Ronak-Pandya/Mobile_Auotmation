package Ronakpandya;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URISyntaxException;


public class AppiumBasics extends BaseTest{
    @Test
    public  void wifiSettingName() throws MalformedURLException, URISyntaxException {

        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();

        driver.findElement(By.xpath("//android.widget.LinearLayout[2]")).click();
        String alterttext = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alterttext, "WiFi settings");
        driver.findElement(By.id("android:id/edit")).sendKeys("RonakWIFI");
        driver.findElement(By.id("android:id/button1")).click();

    }



}
