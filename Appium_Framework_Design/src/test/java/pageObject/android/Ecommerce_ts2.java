package pageObject.android;

import com.aventstack.extentreports.App;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.android.TestUtils.BaseTest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class Ecommerce_ts2 extends BaseTest {


    @Test(dataProvider = "getData", groups = {"Smoke"})
    public void FormFill_test1(HashMap<String, String> input) throws InterruptedException {
        Thread.sleep(5000);

        // FormaPage Section
        formPage.SelectCountry(input.get("country"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Algeria']")).click();
        formPage.setNamefield(input.get("name"));
        driver.hideKeyboard();
        formPage.SelectGender(input.get("gender"));
        ProductCatalogue productCatalogue = formPage.GotoCatalouge();


        //Product Catalogue Section
        productCatalogue.setProducts(0);
        productCatalogue.setProducts(1);
        CartPage cartPage = productCatalogue.GoTOCart();
        Thread.sleep(3000);


        //CartSection
        cartPage.getProductprice();
        Double totalproductsum = cartPage.getTotalproductsum();
        Double totalsum = cartPage.getTotamountl();
        Assert.assertEquals(totalproductsum, totalsum);
        cartPage.Termconditionbutton();
        cartPage.Emailsentcheckbox();
        cartPage.SubmitOrder();



        

    }

    @BeforeMethod
    public void homePageLaunch() {

        formPage.homepage();

    }


    @DataProvider
    public Object[][] getData() throws IOException {
         List<HashMap<String, String>>data=getJsonData(System.getProperty("user.dir") + "//src//test//resources//testdata.json");
        return new Object[][]{
                {data.get(0)},
                {data.get(1)},
                {data.get(2)}
        };
// {{"Name:Ronak Pandya", "Gender:Male", "Country: Algeria"},{"Name: Pandya", "Gender: Female", "Country:Algeria"}}


        // { {},  {} }

    }

}
