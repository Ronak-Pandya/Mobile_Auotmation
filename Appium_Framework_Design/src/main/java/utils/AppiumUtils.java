package utils;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class AppiumUtils {
    public AppiumDriverLocalService service;
    public double formateprice(String s){
        Double d = Double.parseDouble(s.substring(1));
        return d;
    }

    public void webelementWait(WebElement ele, AppiumDriver driver){
        WebDriverWait waitcart = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitcart.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));



    }

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        //System.getProperty("user.dir") + "//src//test//resources//testdata.json"
        String jsonContent = FileUtils.readFileToString(
                new File(jsonFilePath));
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> map = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return map;

    }

    public AppiumDriverLocalService startAppiumservice(String ipaddress, int port){
        service = new AppiumServiceBuilder()
                .withIPAddress(ipaddress)
                .usingPort(port)
                .build();
        service.start();

        return service;

    }
    public String getScreenshot(String testcasename, AppiumDriver driver) throws IOException {

        File source = driver.getScreenshotAs(OutputType.FILE);

        String pathToFile = System.getProperty("user.dir") + "//reports" + testcasename + ".png";


        FileUtils.copyFile(source, new File(pathToFile));
        return pathToFile;

        //Capture and place in folder
        // return file
    }
}
