package Ronakpandya;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ScrollDemo extends BaseTest {

    @Test
    public void ScrollDemoTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        scrollToElement("WebView");


//        scrolltoEndAction();

    }
}
