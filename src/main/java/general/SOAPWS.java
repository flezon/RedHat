package general;
import javax.xml.soap.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;



public class SOAPWS {
	String soapEndpointUrl;
    String soapAction;
    Properties config;
	String configFile="C:\\Users\\ivanbermudez\\eclipse-workspace\\RedHat\\src\\main\\java\\configuracion\\configuracionSOAP.properties";
	public SOAPWS() {
		config = new Properties();
		InputStream input;
		try {
			input = new FileInputStream(configFile);
			config.load(input);
		} catch (Exception e) {
			System.err.println("Error obteniendo archivo de configuracion SOAP "+e.getMessage());
		}
		this.soapEndpointUrl= config.getProperty("ENDPOINT");
		this.soapAction=config.getProperty("ACTION");
		
		
	}
	
	public String sendRequest() {
        String response=callSoapWebService(soapEndpointUrl, soapAction);
        return response;
	}
	
	 public void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();
        String myNamespace = config.getProperty("NAMESPACE");
        String myNamespaceURI = config.getProperty("URI");

        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement(config.getProperty("TAG1"), myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("date", myNamespace);
        soapBodyElem1.addTextNode(config.getProperty("DATE"));
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("processName", myNamespace);
        soapBodyElem2.addTextNode(config.getProperty("PROCESS"));
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("scenarioId", myNamespace);
        soapBodyElem3.addTextNode(config.getProperty("SCENARIO"));
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("variableName", myNamespace);
        soapBodyElem4.addTextNode(config.getProperty("VARIABLE"));
        SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("repositoryId", myNamespace);
        soapBodyElem5.addTextNode(config.getProperty("REPOSITORY"));
        SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("tableName", myNamespace);
        soapBodyElem6.addTextNode(config.getProperty("TABLE"));
    }
	
	 public SOAPMessage createSOAPRequest(String soapAction) throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        createSoapEnvelope(soapMessage);
	        MimeHeaders headers = soapMessage.getMimeHeaders();
	        headers.addHeader("SOAPAction", soapAction);
	        soapMessage.saveChanges();

	        System.out.println("Request SOAP Message:");
	        soapMessage.writeTo(System.out);
	        System.out.println("\n");

	        return soapMessage;
	    }
	
     public String callSoapWebService(String soapEndpointUrl, String soapAction) {
    	 SOAPMessage soapResponse = null; 
    	 try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);

            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            
            System.out.println();
            
            soapConnection.close();
            //return soapResponse.toString();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
		return soapResponse.toString();
    }
	
	//Obtiene el Action a partir de un WSDL
	/*public void getSoapAction() {
		try {
			WSDLFactory factory;
			factory = WSDLFactory.newInstance();
			WSDLReader reader = factory.newWSDLReader();
			Definition definition;
			definition = reader.readWSDL("http://www.w3schools.com/webservices/tempconvert.asmx?wsdl");
			Binding binding = (Binding) definition.getBinding(new QName("http://tempuri.org/", "TempConvertSoap"));
			BindingOperation operation = ((javax.wsdl.Binding) binding).getBindingOperation("CelsiusToFahrenheit", null, null); 
			List extensions =operation.getExtensibilityElements();
			if (extensions != null) {
			    for (int i = 0; i<extensions.size(); i++) {
			        ExtensibilityElement extElement = (ExtensibilityElement) extensions.get(i); 
			        if (extElement instanceof SOAPOperation) {
			            SOAPOperation soapOp = (SOAPOperation) extElement;
			            System.out.println(soapOp.getSoapActionURI());
			        }
			    }
			}
		} catch (Exception e) {
			System.err.println("Error "+e.getMessage());
		}
	}*/
}

