package Ecommerce_testcases;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.management.ThreadMXBean;
import java.time.Duration;

public class Ecommerce_tc1 extends BaseTest {


    @Test
    public void FormFill_test1(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement country = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")));

        country.click();
//        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        scrollToElement("India");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='India']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ronak Pandya");

        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(AppiumBy.className("android.widget.Button")).click();
//        String toastmsg =driver.findElement(AppiumBy.xpath("(//android.widget.Toast[1])")).getAttribute("name");
//        System.out.println(toastmsg);
//        Assert.assertEquals(toastmsg, "Please enter your name");

    }
    @Test
    public void SelectProduct_test2() throws InterruptedException {
        scrollToElement("Jordan 6 Rings");
        int productdisplay = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for(int i=0; i<productdisplay; i++){
            String productname = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();

            if(productname.equalsIgnoreCase("Jordan 6 Rings")){
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(3000);
    }

    @Test
    public void checkCart_test3() throws InterruptedException {
        WebDriverWait waitcart= new WebDriverWait(driver, Duration.ofSeconds(10));
        waitcart.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text","Cart"));
        String productname =driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(productname, "Jordan 6 Rings");
        Thread.sleep(3000);
    }
}
