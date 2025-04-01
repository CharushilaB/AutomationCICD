package charushilaprojects.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
public static ExtentReports getReportObject() {
	String reportFolderPath = System.getProperty("user.dir") + "//reports";
    String reportFilePath = reportFolderPath + "//index.html";

    // ✅ Ensure the reports folder exists
    File reportsFolder = new File(reportFolderPath);
    if (!reportsFolder.exists()) {
        reportsFolder.mkdirs(); // Create the folder if it does not exist
    }

    ExtentSparkReporter reporter = new ExtentSparkReporter(reportFilePath);
    reporter.config().setReportName("Web Automation Results");
    reporter.config().setDocumentTitle("Test Results");

    ExtentReports extent = new ExtentReports();
    extent.attachReporter(reporter);
    extent.setSystemInfo("Tester", "Charushila Bhosale");

    return extent;
	
}
}
