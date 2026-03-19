package Ecommerce_testcases;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.plaf.TableHeaderUI;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Ecommerce_Hybrid_ts3 extends BaseTest {


    @Test
    public void FormFill_test1() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement country = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")));
        country.click();
//      driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        scrollToElement("Algeria");
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Algeria']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Ronak Pandya");

        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(AppiumBy.className("android.widget.Button")).click();


        WebDriverWait waitproducts = new WebDriverWait(driver, Duration.ofSeconds(10));

        waitproducts.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("com.androidsample.generalstore:id/productAddCart")));

        List<WebElement> products = driver.findElements(
                By.id("com.androidsample.generalstore:id/productAddCart"));

        products.get(0).click();
        products.get(1).click();

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(3000);
    }

    @Test
    public void checkCart_test2() throws InterruptedException {
        WebDriverWait waitcart= new WebDriverWait(driver, Duration.ofSeconds(10));
        waitcart.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text","Cart"));
        List<WebElement> productprice = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")); // 0 --> $123.22 and $1343.99;
        int c =productprice.size(); //2
        Double sum =0.0;

        for(int i=0; i<c; i++){
            String priceString =productprice.get(i).getText(); //$124.2;
            Double price = formateprice(priceString);
//            Double price =Double.parseDouble(priceString.substring(1));
            sum +=price;

        }
        String totalprice =  driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Double totalamount = formateprice(totalprice);
        Assert.assertEquals(sum,totalamount);
        WebElement termscondition =  driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        longPressAction(termscondition);
        String tC =  driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
        Assert.assertEquals(tC, "Terms Of Conditions");
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@text='CLOSE']")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.CheckBox[@text='Send me e-mails on discounts related to selected products in future']")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();


    }

    @Test

    public void getcontext_test3() throws InterruptedException {
        Thread.sleep(6000);
        Set<String> context = driver.getContextHandles();
        for(String contexyname : context){
            System.out.println(contexyname);
        }

        driver.context("WEBVIEW_com.androidsample.generalstore");

        WebDriverWait waitbrowser =  new WebDriverWait(driver, Duration.ofSeconds(10));
        waitbrowser.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("q")))).sendKeys("AI courses");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");


    }
}
