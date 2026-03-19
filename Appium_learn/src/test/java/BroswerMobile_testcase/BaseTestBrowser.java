package BroswerMobile_testcase;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class BaseTestBrowser {
    public AppiumDriverLocalService service;
    public  AndroidDriver driver;

    @BeforeClass
    public void configuration() throws URISyntaxException, MalformedURLException {
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 6 Pro API 36.0");
        options.setCapability("uiautomator2ServerLaunchTimeout", 60000);
        options.setChromedriverExecutable("C://Users//Ronak Pandya//Downloads//chromedriver-win64//chromedriver-win64//chromedriver.exe");
        options.setCapability("browserName", "Chrome");

        // Below used for Native mobile >> WebView appears
//        options.setCapability("appium:chromedriverExecutable",
//                "C://Users//Ronak Pandya//Downloads//chromedriver-win64//chromedriver-win64//chromedriver.exe");        options.setApp("C://Users//Ronak Pandya//Downloads//General-Store.apk");
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);


    }
    public void longPressAction(WebElement element){
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element )
                        .getId(), "duration", 2000));

    }

    public void scrolltoEndAction(){
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));
        }while (canScrollMore);

    }
    public void scrollToElement(String text){
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector())"
                                + ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
                )
        );
    }

    public  void swipeAction(WebElement element, String direction){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element ),
                "direction", ""+direction+"",
                "percent", 0.75
        ));
    }
    public void DragAndDropAction(WebElement source, int xcor, int ycor){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) source).getId(),
                "endX", xcor,
                "endY", ycor
        ));
    }

    public double formateprice(String s){
        Double d = Double.parseDouble(s.substring(1));
        return d;
    }

    @AfterClass
    public void teardown(){
        driver.quit();
        service.close();

    }

}
