package gui;

import java.net.URL;
import java.util.Locale;
import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

public class ApplicationLauncher {
	
	public static void main(String[] args) {

		ConfigXML c=ConfigXML.getInstance();
	
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		 
		IsLoginGUI a = new IsLoginGUI();
		a.setFrame(a);
		a.getContentPane().setLayout(null);
		a.setVisible(true);


		try {
			
			BLFacade appFacadeInterface;
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			if (c.isBusinessLogicLocal()) {
				
			 appFacadeInterface=new BLFacadeImplementation();
				
				
			}
			
			else { 
				 String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
				 
			
				URL url = new URL(serviceName);

		        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
		 
		        Service service = Service.create(url, qname);
		 
		        appFacadeInterface = service.getPort(BLFacade.class);
		             
			} 
	
			AdminGUI.setBussinessLogic(appFacadeInterface);		
		}catch (Exception e) {
			
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
	}
}
