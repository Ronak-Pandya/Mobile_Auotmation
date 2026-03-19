package BroswerMobile_testcase.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions {
    AndroidDriver driver;

    public AndroidActions(AndroidDriver driver){
        this.driver = driver;

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
}
