package sn.techabiz.izipay.services;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;


public class JNDIOutils {

	public static Object chercheEJB(RegistreEJB ejb) {
		try {
            InitialContext context = new InitialContext();
            return (Object) context.lookup("java:app/IZIPay-ejb/" + ejb);
        } catch (NamingException ex) {
            Logger.getLogger(JNDIOutils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
	}

}
