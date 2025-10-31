package com.example.tests;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;

public class AllureListener implements ITestListener {

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public byte[] takeScreenshot(byte[] src) { return src; }

    @Override
    public void onTestFailure(ITestResult result) {
        Object obj = result.getInstance();
        try {
            java.lang.reflect.Field f = obj.getClass().getSuperclass().getDeclaredField("driver");
            f.setAccessible(true);
            Object drv = f.get(obj);
            if (drv != null) {
                org.openqa.selenium.WebDriver driver = (org.openqa.selenium.WebDriver) drv;
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                byte[] fileContent = FileUtils.readFileToByteArray(srcFile);
                takeScreenshot(fileContent);
            }
        } catch (Exception e) {
            // ignore
        }
    }
}
