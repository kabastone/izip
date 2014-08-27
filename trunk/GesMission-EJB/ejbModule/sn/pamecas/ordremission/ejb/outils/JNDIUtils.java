package sn.pamecas.ordremission.ejb.outils;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.security.authorization.resources.EJBResource;

public class JNDIUtils  {
	
	public static Object lookUpEJB(EJBRegistry ejbRegistry){
		
		try {
			InitialContext initialContext = new InitialContext();
			return initialContext.lookup("java:app/GesMission-EJB/"+ejbRegistry.name());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
