package pageObject.android.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
    static ExtentReports reports;

    public static ExtentReports getReportsObject(){
        String path = System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Automation-Results");
        reporter.config().setDocumentTitle("Test Results");

        reports =  new ExtentReports();
        reports.attachReporter(reporter);
        reports.setSystemInfo("Tester", "Ronak Pandya");
        return reports;

    }
}
