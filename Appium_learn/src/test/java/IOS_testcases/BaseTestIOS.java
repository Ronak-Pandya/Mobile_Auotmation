package IOS_testcases;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BaseTestIOS {
    public AppiumDriverLocalService service;
    public IOSDriver driver;

    public void configuration() throws URISyntaxException, MalformedURLException {
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();

        XCUITestOptions options =  new XCUITestOptions();
        //To get Device name
        //options.setDeviceName();


        // To get APP
        //options.setApp();

        //To set Version
        // options.setPlatformVersion();

        //Appium -->> Webdriver -->> IOS APP
        //So in ios it not direct run using XCUITestOptions it required webdriver to automate
        // for that need a small time out
        // options.setWdaLaunchTimeout(Duration.ofSeconds(10));

        driver = new IOSDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        





    }
}
