package general;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static int waitingTime=60;
	public static String chromeDriverLocation="C:\\Users\\carlosquimbayo\\eclipse-workspace\\demo-bbva\\src\\main\\java\\chromedriver.exe";
	public static String screenshotFolder="";
	public static String key="1";
	
	public Utils() {
		System.setProperty("webdriver.chrome.driver",chromeDriverLocation);
        driver = new ChromeDriver();
    	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-hhmm");
        Date date = new Date();
        screenshotFolder=dateFormat.format(date);
	}
	
	public static void screenshot(String descripcion) {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("C:\\Users\\carlosquimbayo\\Documents\\Screenshots\\"+screenshotFolder+"\\"+descripcion+".png"));
		} catch (IOException e) {
			System.err.println("No se pudo tomar el pantallazo "+e.getMessage());
		}
	}
	
	public static void waitObject(String wb,int waitTime) {
        wait = new WebDriverWait(driver, waitTime);
        try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb)));
        }catch(Exception e) {
        	System.err.println("No se pudo cargar el objeto "+e.getMessage());
        }
	}
	
	public static WebElement objeto(String xpath) {
		WebElement elemento=driver.findElement(By.xpath(xpath));
		return elemento;
	}
}
