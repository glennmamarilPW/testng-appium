package test;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pages.calculator.Calculator;
import base.BaseClass;
import base.TestBase;

public class Trial extends TestBase {
	
	AppiumDriver driver;
	Calculator calculator;
	
	@BeforeTest
	public void setup() {
		try {
			
			driver = initializeDriver();
			calculator = new Calculator(driver);
			//PageFactory.initElements(new AppiumFieldDecorator(driver), Calculator.class);
			//PageFactory.initElements(getDriver(), Calculator.class);
		}catch(Exception exp) {
			System.out.println("Cause is: "+exp.getCause());
			System.out.println("Message is: "+exp.getMessage());
			exp.printStackTrace();
		}
	}
	
	@Test
	public void sampleTest() throws InterruptedException {
		calculator.clickNumber("1");
		driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
		calculator.clickNumber("2");
		driver.findElement(By.id("com.android.calculator2:id/eq")).click();
		Thread.sleep(3000);
		
	}
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
