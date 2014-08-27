package sn.pamecas.ordremission.web.demande.controller;

import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import sn.pamecas.ordremission.ejb.entites.DemandeTDR;
import sn.pamecas.ordremission.ejb.outils.EJBRegistry;
import sn.pamecas.ordremission.ejb.outils.JNDIUtils;
import sn.pamecas.ordremission.ejb.session.DemandeTDRFacade;
import sn.pamecas.ordremission.web.outils.SendMail;
import sn.pamecas.ordremission.web.ressources.authentification.UserInfo;

public class ValiderRessourceSuperviseur {
	private DemandeTDR demande = new DemandeTDR();
	private String urlFichier;
	private UserInfo<?> userInfo ;

	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Window comp) {
		Map<String, Object> params = (Map<String, Object>) Executions
				.getCurrent().getArg();
		demande = (DemandeTDR) params.get("demande");
		urlFichier = demande.getFilePath();
		Session session = Sessions.getCurrent();
		         userInfo = (UserInfo<?>) session.getAttribute("userInfo");
	}

	@Command
	public void valider(@ContextParam(ContextType.VIEW) Window comp) {
		DemandeTDRFacade demandeFacade = (DemandeTDRFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.DemandeTDRFacade);
		demande.setIsSuperviseurAllowed(true);
		demandeFacade.edit(demande);
		String from = "nmcamara@pamecas.sn";
		String to = "dniang@pamecas.sn";
		String subject = "Validtaion ordre de mission";
		String text = "Vous avez reçu une demande d'ordre de mission."
				+ "\n \n Connectez vous sur l'application pour procéder à une validation.  "
				+ "\n http://localhost:8080/GesMission-WEB/index.zul";
		SendMail mailto = new SendMail();
		mailto.send(from, to, subject, text);
		Messagebox.show("Ressource Validée");
		// TODO send message patrimoine
		comp.detach();

	}

	@Command
	public void annuler(@ContextParam(ContextType.VIEW) Window comp) {
		DemandeTDRFacade demandeFacade = (DemandeTDRFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.DemandeTDRFacade);
      
		String from = userInfo.getEmail();
		String to = demande.getUtilisateur().getEmail();
		String subject = "Annulation ordre de mission";
		String text = "Votre demande d'ordre de mission a été rejetée par le superviseur.";
		//SendMail mailto = new SendMail();
		//mailto.send(from, to, subject, text);

		
		Messagebox.show("Ressource annulée");
		// TODO send message demandeur
		comp.detach();

	}

	public String getUrlFichier() {
		return urlFichier;
	}

	public void setUrlFichier(String urlFichier) {
		this.urlFichier = urlFichier;
	}
}
