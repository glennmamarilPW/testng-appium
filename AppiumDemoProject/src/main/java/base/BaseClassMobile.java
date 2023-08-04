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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import pages.calculator.Calculator;

public class BaseClassMobile {

		public AppiumDriver mobileDriver;
		public String appiumServer = "127.0.0.1";
		public int appiumPort = 4723;
		URL appiumURL = null;
		public static Properties prop;
		
		public BaseClassMobile() {
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
		//@Parameters({"platformName", "platformVersion", "appPackage", "appActivity","automationName","deviceName"})//These parameters can be configured in testng_Agile.xml
		//@Parameters({"categoryXML", "browser", "url", "td"})//These parameters can be configured in testng_Agile.xml
		@BeforeClass
		//@BeforeMethod
		//String platform = platformName;
		
		public void setup() {
			try {
				mobileDriver = initializeDriver();
				
			}catch(Exception exp) {
				System.out.println("Cause is: "+exp.getCause());
				System.out.println("Message is: "+exp.getMessage());
				exp.printStackTrace();
			}
		}
		
		public AppiumDriver getMobileDriver() {
			//System.out.println(mobileDriver);
			return mobileDriver;
		}
		
		
		
		public AppiumDriver initializeDriver() {
			try {
				/*Appium URL setup is dependent on the current Appium version
				 * for versions 1.2 and below, such as Appium Desktop, "/wd/hub" should be added at the end
				 * for versions 2.0 onwards, that string is no longer necessary
				 * Note that Appium can still automate mobile apps regardless of Appium version
				 * currently still placed here, in case user is currently using an older version of Appium*/
				appiumURL = new URL("http://"+appiumServer+":"+appiumPort+"/wd/hub"); //for Appium 1.2 and lower
				//appiumURL = new URL("http://"+appiumServer+":"+appiumPort); //for Appium 2.0 onwards
				this.mobileDriver = new AppiumDriver(appiumURL,setAppCapabilities());
				System.out.println(this.mobileDriver);
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
		
		@AfterClass(alwaysRun=true)
		public void tearDown() {
			mobileDriver.quit();
		}
}
