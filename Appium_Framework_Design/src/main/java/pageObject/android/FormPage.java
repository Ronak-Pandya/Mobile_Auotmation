package pageObject.android;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class FormPage extends AndroidActions {
    AndroidDriver driver;

    public FormPage(AndroidDriver driver){
        super(driver);
        this.driver =driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countrydropdown;

//    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Algeria']")
//    private WebElement countryname;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement namefield;



    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleOption;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement maleOption;

    @AndroidFindBy(className = "android.widget.Button")
    private WebElement letGobutton;
    public void setNamefield(String name){
        namefield.sendKeys(name);
    }

    public void SelectGender(String gender){

        if(gender.contains("Male")){
            maleOption.click();
        }
        else{
            femaleOption.click();
        }
    }

    public void SelectCountry(String country){
        countrydropdown.click();
        scrollToElement(country);

    }

    public ProductCatalogue GotoCatalouge(){
        letGobutton.click();
        return new ProductCatalogue(driver);
    }

    public void homepage(){

            driver.terminateApp("com.androidsample.generalstore");
            driver.activateApp("com.androidsample.generalstore");

        }
    

}
