package Funcionalidades;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import general.Utils;

public class CheckSAM {
	Properties prop;
	WebDriver driver;
	WebDriverWait wait;
	String configFile="C:\\Users\\carlosquimbayo\\Documents\\RedHat\\src\\main\\java\\propiedades\\CheckSAM.properties";
	
	public CheckSAM () {
		this.driver=Utils.driver;
		this.wait=Utils.wait;
		prop = new Properties();
		try {
			InputStream input = new FileInputStream(configFile);
			prop.load(input);
		} catch (Exception e) {
			System.err.println("No se pudo cargar el archivo de propiedades "+e.getMessage());
		}
	}
	
	public void fakeTest() {
		Utils.waitObject(prop.getProperty("general"), 60);
		Utils.objeto(prop.getProperty("general")).click();
		String nombreEscenario="escenario";
		Utils.screenshot("Captura de escenario "+nombreEscenario);
		Utils.waitObject(prop.getProperty("baseDatos"), 60);
		Utils.objeto(prop.getProperty("baseDatos")).click();
		Utils.objeto(prop.getProperty("variables")).click();
		String nombreVariables="variable";
		Utils.screenshot("Captura de variables "+nombreVariables);
		Utils.waitObject(prop.getProperty("baseDatos"), 60);
		Utils.objeto(prop.getProperty("baseDatos")).click();
		Utils.objeto(prop.getProperty("atributos")).click();
		String nombreAtributos="atributos";
		Utils.screenshot("Captura de atributo "+nombreAtributos);
		Utils.waitObject(prop.getProperty("baseDatos"), 60);
		Utils.objeto(prop.getProperty("baseDatos")).click();
		Utils.objeto(prop.getProperty("repositorios")).click();
		String nombreRepositorio="repositorios";
		Utils.screenshot("Captura de repositorio "+nombreRepositorio);
		
	}
	
	public void checkVariables() {
		Utils.waitObject(prop.getProperty("baseDatos"), 60);
		Utils.objeto(prop.getProperty("baseDatos")).click();
		Utils.objeto(prop.getProperty("variables")).click();
		Utils.waitObject(prop.getProperty("estado"), 60);
		Utils.objeto(prop.getProperty("estado")).click();
		Utils.objeto(prop.getProperty("estadoAbierto")).click();
		Utils.objeto(prop.getProperty("variableSpace")).sendKeys("PromPonPRec");
		Utils.objeto(prop.getProperty("buscar")).click();
		//Utils.waitObject(prop.getProperty("txtVariables"), 60);
		//String nombreVariables=Utils.objeto(prop.getProperty("txtVariables")).getText();
		String nombreVariables="variable";
		Utils.screenshot("Captura de variables "+nombreVariables);
	}
	
	public void checkAtributos() {
		Utils.waitObject(prop.getProperty("baseDatos"), 60);
		Utils.objeto(prop.getProperty("baseDatos")).click();
		Utils.objeto(prop.getProperty("atributos")).click();
		Utils.waitObject(prop.getProperty("estado"), 60);
		Utils.objeto(prop.getProperty("estado")).click();
		Utils.objeto(prop.getProperty("estadoAbierto")).click();
		Utils.objeto(prop.getProperty("buscar")).click();
		//Utils.waitObject(prop.getProperty("txtAtributos"), 60);
		//String nombreAtributos=Utils.objeto(prop.getProperty("txtAtributos")).getText();
		String nombreAtributos="atributos";
		Utils.screenshot("Captura de atributo "+nombreAtributos);
	}
	
	public void checkRepositorios() {
		Utils.waitObject(prop.getProperty("baseDatos"), 60);
		Utils.wait.until(ExpectedConditions.elementToBeClickable(Utils.objeto(prop.getProperty("baseDatos"))));
		Utils.objeto(prop.getProperty("baseDatos")).click();
		Utils.objeto(prop.getProperty("repositorios")).click();
		
		Utils.waitObject(prop.getProperty("estado"), 60);
		Utils.objeto(prop.getProperty("estado")).click();
		Utils.waitObject(prop.getProperty("estadoAbierto"), 60);
		Utils.objeto(prop.getProperty("estadoAbierto")).click();
		Utils.objeto(prop.getProperty("buscar")).click();
		//Utils.waitObject(prop.getProperty("txtRepositorio"), 60);
		//String nombreRepositorio=Utils.objeto(prop.getProperty("txtRepositorio")).getText();
		String nombreRepositorio="repositorios";
		Utils.screenshot("Captura de repositorio "+nombreRepositorio);
	}
	
	public void checkScenario() {
		Utils.waitObject(prop.getProperty("general"), 60);
		Utils.objeto(prop.getProperty("general")).click();
		Utils.objeto(prop.getProperty("escenarios")).click();
		Utils.waitObject(prop.getProperty("estado"), 60);
		Utils.objeto(prop.getProperty("estado")).click();
		Utils.waitObject(prop.getProperty("estadoAbierto"), 60);
		Utils.objeto(prop.getProperty("estadoAbierto")).click();
		Utils.objeto(prop.getProperty("buscar")).click();
		//Utils.waitObject(prop.getProperty("txtEscenario"), 60);
		//String nombreEscenario=Utils.objeto(prop.getProperty("txtEscenario")).getText();
		String nombreEscenario="escenario";
		Utils.screenshot("Captura de escenario "+nombreEscenario);
	}
	
	
}
