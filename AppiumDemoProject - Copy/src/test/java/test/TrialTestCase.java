package test;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pages.calculator.Calculator;
import base.BaseClass;
import base.BaseClassMobile;

public class TrialTestCase extends BaseClassMobile {
	
	AppiumDriver mobileDriver;
	Calculator calculator;
	
	private void initialize() {
		mobileDriver = getMobileDriver();
		calculator = new Calculator(mobileDriver);
	}
	
	@Test
	public void sampleTest() throws InterruptedException {
		initialize();
		String expression = "1+2=";
		calculator.parseExpression(expression);
		Assert.assertTrue(calculator.verifyResult(), "Result not the same!");
		Thread.sleep(3000);
	}
	
}
