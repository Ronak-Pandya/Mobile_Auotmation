package pageObject.android.TestUtils;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pageObject.android.FormPage;
import utils.AppiumUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class BaseTest extends AppiumUtils {
    public AppiumDriverLocalService service;
    public AndroidDriver driver;
    public FormPage formPage;




    @BeforeMethod(alwaysRun = true)
    public void configuration() throws URISyntaxException, IOException {

        service =startAppiumservice("127.0.0.1", 4723);


//        service= startAppiumservice(ipAddress, Integer.parseInt("port"));
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 6 Pro API 36.0");
        options.setCapability("appium:chromedriverExecutable",
                "C://Users//Ronak Pandya//Downloads//chromedriver-win64//chromedriver-win64//chromedriver.exe");
        // App path

        options.setApp("C://Users//Ronak Pandya//Downloads//General-Store.apk");
        driver = new AndroidDriver(service.getUrl(), options);
        formPage =  new FormPage(driver);



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

    @AfterMethod(alwaysRun = true)
    public void teardown(){
        driver.quit();
        service.close();

    }

}
