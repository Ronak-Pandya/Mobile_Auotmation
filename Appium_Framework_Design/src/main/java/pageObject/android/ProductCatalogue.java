package pageObject.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

import java.util.List;

public class ProductCatalogue extends AndroidActions {
    AndroidDriver driver;

    public ProductCatalogue(AndroidDriver driver) {
        super(driver);
        this.driver =driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this );

    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
    private List<WebElement> products;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cart;




    public void setProducts(int val){

        products.get(val).click();
    }

    public CartPage GoTOCart(){
        cart.click();
        return new CartPage(driver);
    }


}
