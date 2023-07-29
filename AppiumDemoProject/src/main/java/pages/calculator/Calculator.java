package pages.calculator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import utilities.UserHelper;
//import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Calculator extends TestBase{
	public AppiumDriver driver;
	/*----------Toolbar----------*/
//	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc=\"More options\"]")
//	private static WebElement options;
//	
//	/*----------More Options----------*/
//	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.TextView")
//	private static WebElement history;
//	
//	@AndroidFindBy(xpath="/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView")
//	private static WebElement openSourceLicenses;
	
	/*----------Calculator Functions----------*/
	@FindBy(id="com.android.calculator2:id/op_add")
	private static WebElement add;
	
	@FindBy(id="com.android.calculator2:id/op_sub")
	private static WebElement subtract;
	
	@FindBy(id="com.android.calculator2:id/op_mul")
	private static WebElement multiply;
	
	@FindBy(id="com.android.calculator2:id/op_div")
	private static WebElement divide;
	
	@FindBy(id="com.android.calculator2:id/clr")
	private static WebElement clear;
	
	@FindBy(id="com.android.calculator2:id/eq")
	private static WebElement equal;
	
	@FindBy(id="com.android.calculator2:id/dec_point")
	private static WebElement decimal;
	
	@FindBy(id="com.android.calculator2:id/del")
	private static WebElement delete;
	
	@FindBy(id="com.android.calculator2:id/formula")
	private static WebElement formula;
	
	@FindBy(id="com.android.calculator2:id/result")
	private static WebElement result;
	
	@FindBy(id="com.android.calculator2:id/pad_advanced")
	private static WebElement advancedOperations;
//	
	public Calculator(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickNumber(String number) {
		WebElement numberElement = driver.findElement(By.id("com.android.calculator2:id/digit_"+number));
		numberElement.click();
		//reportPass(Thread.currentThread().getStackTrace()[1].getMethodName(), "Entered a number");
	}
	
	public void Operation(String expression) {
		switch(expression) {
		case "+":
			add.click();
			break;
		case "-":
			subtract.click();
			break;
		case "*":
			multiply.click();
			break;
		case "/":
			divide.click();
			break;
		}
	}
}
