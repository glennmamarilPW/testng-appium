package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import pages.calculator.Calculator;

public class TestBase {

		public AppiumDriver mobileDriver;
		public String appiumServer = "127.0.0.1";
		public int appiumPort = 4723;
		URL appiumURL = null;
		public static Properties prop;
		
		public TestBase() {
			try {
				prop = new Properties();
				FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com"+"/qa/config/OR.properties");
				prop.load(ip);
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		public AppiumDriver initializeDriver() {
			try {
				//appiumURL = new URL("http://"+appiumServer+":"+appiumPort+"/wd/hub"); //for appium 1.2 and lower
				appiumURL = new URL("http://"+appiumServer+":"+appiumPort); //for appium 2.0 onwards
				this.mobileDriver = new AppiumDriver(appiumURL,setAppCapabilities());
			}catch(Exception e){
				e.printStackTrace();
			}
			return mobileDriver;
		}
		
		public DesiredCapabilities setAppCapabilities() {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("platformName", "Android");
			cap.setCapability("platformVersion", "8.0");
			cap.setCapability("appPackage", "com.android.calculator2");
			cap.setCapability("appActivity", "com.android.calculator2.Calculator");
			cap.setCapability("automationName", "UIAutomator2");
			cap.setCapability("deviceName", "emulator-5554");
			return cap;
		}
		
		@SuppressWarnings("rawtypes")
		public String takeScreenShotPath(String testCaseName,AppiumDriver mobileDriver) throws IOException {
			TakesScreenshot ts = (TakesScreenshot) mobileDriver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String destinationFile = System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
			FileUtils.copyFile(source, new File(destinationFile));
			return destinationFile;
		}
		
//		@BeforeTest
//		public void setup() {
//			try {
//				mobileDriver = initializeDriver();
//				//calculator = new Calculator(mobileDriver);
//			}catch(Exception exp) {
//				System.out.println("Cause is: "+exp.getCause());
//				System.out.println("Message is: "+exp.getMessage());
//				exp.printStackTrace();
//			}
//		}
		
		@AfterClass(alwaysRun=true)
		public void tearDown() {
			mobileDriver.quit();
		}
}
