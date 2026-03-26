package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegressionPage;

// ...existing code...
// Assumes BaseTest provides initialized `driver` for local/Appium/BrowserStack runs.
public class RegressionPageTest extends BaseTest {

    @Test(description = "Validate Regression page execution flow")
    public void verifyRegressionRun() {
        RegressionPage regressionPage = new RegressionPage(driver);

        Assert.assertTrue(regressionPage.isLoaded(), "Regression page did not load.");
        regressionPage.runRegression();
        Assert.assertTrue(regressionPage.isSuccessVisible(), "Regression run success message not visible.");
    }
}
// ...existing code...

