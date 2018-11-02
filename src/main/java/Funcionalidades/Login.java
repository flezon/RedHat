package Funcionalidades;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import general.Utils;

public class Login {
	Properties prop;
	Properties config;
	WebDriver driver;
	WebDriverWait wait;
	String propertyFile="C:\\Users\\ivanbermudez\\eclipse-workspace\\RedHat\\src\\main\\java\\propiedades\\login.properties";
	String configFile="C:\\Users\\ivanbermudez\\eclipse-workspace\\RedHat\\src\\main\\java\\configuracion\\configuracionLogin.properties";
	public Login() {
		this.driver=Utils.driver;
		this.wait=Utils.wait;
		prop = new Properties();
		config=new Properties();
		try {
			InputStream inputConfig = new FileInputStream(configFile);
			InputStream inputProperty = new FileInputStream(propertyFile);
			prop.load(inputProperty);
			config.load(inputConfig);
		} catch (Exception e) {
			System.err.println("No se pudo cargar el archivo de propiedades "+e.getMessage());
		}
	}
	
	public void makeLogin() {
		//Abre la URL
        driver.get("http://172.16.2.143:8080/xm-hgc/#/login");
        //espera que cargue la página
        Utils.waitObject(prop.getProperty("usuario"), 60);
        //toma pantallazo
        Utils.screenshot("Login");
        Utils.objeto(prop.getProperty("usuario")).sendKeys(config.getProperty("USUARIO"));
        Utils.objeto(prop.getProperty("password")).sendKeys(config.getProperty("PASSWORD"), Utils.key);
        Utils.objeto(prop.getProperty("btnEntrar")).click();
        Utils.screenshot("Finalizar login");
	}
	
	
	
	
}
