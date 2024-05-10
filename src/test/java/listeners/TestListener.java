package listeners;

import com.aventstack.extentreports.Status;
import helpers.CaptureHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentReportManager;
import reports.ExtentTestManager;
import utils.driver.Log;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }


    @Override
    public void onStart(ITestContext result) {
        System.out.println("onStart" + result.getStartDate());
        CaptureHelper.startRecord(result.getName());
    }

    @Override
    public void onFinish(ITestContext result) {
        System.out.println("onStart" + result.getEndDate());
        // ket thuc va thuc thi extents report
        ExtentReportManager.getExtentReports().flush();
        CaptureHelper.stopRecord();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info(result.getName() + " is pass.");
        ExtentTestManager.logMessage(Status.PASS, result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        System.out.println(result.getName() + " is fail ");
        CaptureHelper.TakeScreenshot(result);
        Log.info(result.getName() + " is fail.");

        ExtentTestManager.addScreenShot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, result.getName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }
}
