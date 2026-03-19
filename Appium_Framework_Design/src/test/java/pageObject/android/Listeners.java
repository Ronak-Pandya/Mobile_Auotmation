package pageObject.android;

import com.aventstack.extentreports.*;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pageObject.android.TestUtils.ExtentReportsNG;
import utils.AppiumUtils;

import java.io.File;
import java.io.IOException;

public class Listeners extends AppiumUtils implements ITestListener {
    AppiumDriver driver;
    ExtentTest test;
    ExtentReports extent = ExtentReportsNG.getReportsObject();

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
         test = extentTest.get();

        if (test == null) {
            test = extent.createTest(result.getMethod().getMethodName());
            extentTest.set(test);
        }

        test.fail(result.getThrowable());

        try {
            driver = (AppiumDriver) result.getTestClass()
                    .getRealClass()
                    .getField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            test.addScreenCaptureFromPath(getScreenshot(result.getMethod().getMethodName(), driver), result.getMethod().getMethodName());

        }
        catch (IOException e){
            e.printStackTrace();
        }


    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}