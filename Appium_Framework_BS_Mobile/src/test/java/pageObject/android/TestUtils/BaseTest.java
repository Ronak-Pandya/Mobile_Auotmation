package pageObject.android.TestUtils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObject.android.FormPage;
import utils.AppiumUtils;

import java.net.URI;

public class BaseTest extends AppiumUtils {
    public AppiumDriverLocalService service;
    public AndroidDriver driver;
    public FormPage formPage;

    @BeforeMethod(alwaysRun = true)
    public void configuration() throws Exception {
        String executionMode = System.getProperty("execution", "local").trim().toLowerCase();

        UiAutomator2Options options = new UiAutomator2Options();

        if ("browserstack".equals(executionMode)) {
            // BrowserStack SDK(javaagent) + browserstack.yml handles most caps.
            driver = new AndroidDriver(URI.create("https://hub-cloud.browserstack.com/wd/hub").toURL(), options);
        } else {
            service = startAppiumservice("127.0.0.1", 4723);

            options.setDeviceName(System.getProperty("deviceName", "Pixel 6 Pro API 36.0"));

            String chromeDriverPath = System.getProperty("chromedriverPath", "").trim();
            if (!chromeDriverPath.isEmpty()) {
                options.setCapability("appium:chromedriverExecutable", chromeDriverPath);
            }

            String appPath = System.getProperty("appPath", "C://Users//Ronak Pandya//Downloads//General-Store.apk");
            options.setApp(appPath);

            driver = new AndroidDriver(service.getUrl(), options);
        }

        formPage = new FormPage(driver);
    }

    public void longPressAction(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "mobile: longClickGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "duration", 2000
                )
        );
    }

    public void scrolltoEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript(
                    "mobile: scrollGesture",
                    ImmutableMap.of(
                            "left", 100,
                            "top", 100,
                            "width", 200,
                            "height", 200,
                            "direction", "down",
                            "percent", 3.0
                    )
            );
        } while (canScrollMore);
    }

    public void scrollToElement(String text) {
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector())"
                                + ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
                )
        );
    }

    public void swipeAction(WebElement element, String direction) {
        ((JavascriptExecutor) driver).executeScript(
                "mobile: swipeGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "direction", direction,
                        "percent", 0.75
                )
        );
    }

    public void DragAndDropAction(WebElement source, int xcor, int ycor) {
        ((JavascriptExecutor) driver).executeScript(
                "mobile: dragGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) source).getId(),
                        "endX", xcor,
                        "endY", ycor
                )
        );
    }

    public double formateprice(String s) {
        return Double.parseDouble(s.substring(1));
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}
