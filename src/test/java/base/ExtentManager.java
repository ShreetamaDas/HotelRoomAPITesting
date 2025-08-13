package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            String reportDir = "target/ExtentReports";
            new File(reportDir).mkdirs();
            String file = reportDir + File.separator + "HotelRoomApiTestReport.html";

            ExtentSparkReporter spark = new ExtentSparkReporter(file);
            spark.config().setDocumentTitle("Hotel Room API Automation Report");
            spark.config().setReportName("Hotel Room API Test Results");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Automation");
        }
        return extent;
    }
}
