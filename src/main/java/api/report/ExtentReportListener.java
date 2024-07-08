package api.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ExtentReportListener implements ITestListener {

    private static final String OUTPUT_FOLDER = "./test-output/";
    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    static {
        try {
            extentReports = createExtentReports();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ExtentReports createExtentReports() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String fileName = "TestExecutionReport_" + timeStamp + ".html";
        Path path = Paths.get(OUTPUT_FOLDER, fileName);
        Files.createDirectories(path.getParent());

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path.toString());
        htmlReporter.config().setDocumentTitle("API Test Automation Report");
        htmlReporter.config().setReportName("API Test Execution");
        htmlReporter.config().setTheme(Theme.DARK);

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        return extentReports;
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().pass(result.getMethod().getMethodName() + " test passed");
        if (Objects.requireNonNull(result.getParameters()).length != 0) {
            Object[] parameters = result.getParameters();
            StringBuilder methodSignature = new StringBuilder(result.getMethod().getMethodName() + " test case parameters (");
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    methodSignature.append(parameters[i]);
                    if (i < parameters.length - 1) {
                        methodSignature.append(", ");
                    }
                }
            }
            methodSignature.append(")");
            extentTest.get().log(Status.INFO, methodSignature.toString());
        }
        extentTest.get().log(Status.INFO, "Test duration: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        extentTest.get().fail(result.getThrowable());
        extentTest.get().log(Status.INFO, "Test duration: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().skip(result.getThrowable());
        extentTest.get().log(Status.INFO, "Test duration: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }
}
