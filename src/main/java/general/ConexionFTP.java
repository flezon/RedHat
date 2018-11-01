package general;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

public class ConexionFTP {
	FTPSClient ftp = null;
	Properties config;
	String configFile="C:\\Users\\ivanbermudez\\eclipse-workspace\\RedHat\\src\\main\\java\\configuracion\\configuracionFTP.properties";
	public ConexionFTP() {
		config = new Properties();
		try {
			InputStream input = new FileInputStream(configFile);
			config.load(input);
		} catch (Exception e) {
			System.err.println("No se pudo cargar el archivo de propiedades "+e.getMessage());
		}
		
	}
	
	public void conectar() {
		String host=config.getProperty("host");
		int port=Integer.parseInt(config.getProperty("port"));
		String username=config.getProperty("username");
		String password=CryptDecrypt.decrypt(config.getProperty("password"), Utils.key);
		ftp = new FTPSClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        try {
        ftp.connect(host,port);
        System.out.println("FTP URL is:"+ftp.getDefaultPort());
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }
        boolean a=ftp.login(username, password);
        System.out.println("el login fue "+a);
        //esto es necesario porque es una conexión segura con SSL
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.execPBSZ(0);  // protección al tamaño del buffer
        ftp.execPROT("P"); // protección al canal de data en privado
		}catch(Exception e) {
			System.err.println("Conexión con FTP fallida "+e.getMessage());
        }
        ftp.enterLocalPassiveMode();     
	}
	
	public void desconectar(){
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException e) {
                System.err.println("No se pudeo desconectar del servidor FTP "+e.getMessage());
            }
        }
    }
	
	public void descargarArchivo(String source, String destination) {
        try{
        	File downloadFile1 = new File(destination);
        	//no funciona sin esta linea no se porque
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
        	FileOutputStream fos = new FileOutputStream(destination);
            try {
            	this.ftp.retrieveFile(source,fos);
            }catch(Exception e) {
            	System.out.println("error obteniendo archivo "+e.getMessage());
            }
            outputStream1.flush();
            outputStream1.close();
            fos.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void cargarArchivo(String localFileFullName, String fileName, String hostDir){
                try {
                InputStream input = new FileInputStream(new File(localFileFullName));
                this.ftp.storeFile(hostDir + fileName, input);
                }
                catch(Exception e){
                	System.err.println("Error cargando archivo "+e.getMessage());
                }
            }
}
