package Funcionalidades;

import java.math.BigInteger;
import java.security.MessageDigest;


import general.ConexionFTP;
import general.CryptDecrypt;
import general.SOAPWS;
import general.Utils;


public class StartBrowser {
	public static void main(String[] args) {
        @SuppressWarnings("unused")
		Utils util=new Utils();
        sendSoap();
        //System.out.println(CryptDecrypt.encrypt("8ugufrUy", "1"));
    }

	public static void sendSoap() {
		SOAPWS soap=new SOAPWS();
		String resp=soap.sendRequest();
	}	
}