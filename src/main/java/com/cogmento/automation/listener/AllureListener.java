package com.cogmento.automation.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.cogmento.automation.base.TestBase;

import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AllureListener implements ITestListener {

	private static String getMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachment for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String printMessage(String message) {
		return message;
	}

	@Override
	public void onTestStart(ITestResult result) {
		log.info("I am in on Start method {} ", result.getName());
		result.setAttribute("WebDriver", TestBase.driver);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("I am in on Test Success method {} ", result.getName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.info("I am in on Test Success method {} ", result.getName());
		result.getInstance();
		WebDriver driver = TestBase.driver;
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			log.info("Screenshot captured for test case:" + getMethodName(result));
			saveScreenshotPNG(driver);
		}
		// Save a log on allure.
		printMessage(getMethodName(result) + " failed and screenshot is captured!");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.info("I am in on Test Success method {} ", result.getName());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		log.info("I am in  onTestFailedButWithinSuccessPercentage method {} ", result.getName());

	}

	@Override
	public void onStart(ITestContext context) {
		log.info("I am in on onStart method {} ", context.getName());

	}

	@Override
	public void onFinish(ITestContext context) {
		log.info("I am in  onFinish method {} ", context.getName());

	}

}
