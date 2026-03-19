package BroswerMobile_testcase;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Browser_TS1 extends BaseTestBrowser{

    @Test
    public void browser_Test1(){
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");

        // Click hamburger menu
        driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();

        // Click products
        driver.findElement(By.cssSelector("a[routerlink*='products']")).click();

        // Scroll down
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");

        // Get product text
        String text = driver.findElement(By.cssSelector("a[href*='products/3']")).getText();

        // Assertion
        Assert.assertEquals(text, "Devops");

//            driver.get("https://www.google.com/");
//
//            WebDriverWait waitbrowser = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//            WebElement searchBox =
//                    waitbrowser.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
//
//            searchBox.sendKeys("AI courses");
//            searchBox.sendKeys(Keys.ENTER);
        }



}
