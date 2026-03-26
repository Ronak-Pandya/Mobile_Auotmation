package pageObject.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;

import java.util.List;

public class CartPage extends AndroidActions {
    AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productprice;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totamountl;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private WebElement termconditionbutton;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='CLOSE']")
    private WebElement closebutton;

    @AndroidFindBy(xpath = "//android.widget.CheckBox[@text='Send me e-mails on discounts related to selected products in future']")
    private WebElement emailsentcheckbox;

    @AndroidFindBy(id ="com.androidsample.generalstore:id/btnProceed")
    private WebElement btnProceed;

    public List<WebElement> getProductprice() {
        return productprice;
    }

    public Double getTotalproductsum() {
        //    int c =productprice.size(); //2
        int c = productprice.size();
        Double sum = 0.0;

        for (int i = 0; i < c; i++) {
            String priceString = productprice.get(i).getText(); //$124.2;
            Double price = formateprice(priceString);
            sum += price;

        }
        return sum;
    }

    public Double getTotamountl() {

        return formateprice(totamountl.getText());

    }
    public void Termconditionbutton() {
        longPressAction(termconditionbutton);
        closebutton.click();
    }

    public void Emailsentcheckbox(){
        emailsentcheckbox.click();
    }
    public void SubmitOrder(){
        btnProceed.click();
    }
}
