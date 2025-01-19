package core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	private static WebDriver driver;
	
	private DriverFactory() {}
	
	public static WebDriver getDriver(){
		if(driver == null) {			
			driver = new FirefoxDriver();			
			System.setProperty("webdriver.gecko.driver", "./src/main/resources/Drivers/geckodriver.exe");
			driver.manage().window().setSize(new Dimension(1200, 765));
			
			//switch (Propriedades.browser) {
				//case FIREFOX: driver = new FirefoxDriver(); break;
				//case CHROME: driver = new ChromeDriver(); break;
			//}
						
		}
		return driver;
	}

	public static void killDriver(){
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
