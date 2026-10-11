package practical;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestResultListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("\n[RUNNING] " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[PASS] " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[FAIL] " + result.getMethod().getMethodName());
        if (result.getThrowable() != null) {
            System.out.println("Reason: " + result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[SKIPPED] " + result.getMethod().getMethodName());
    }
}
