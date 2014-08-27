package sn.pamecas.ordremission.web.demande.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jboss.crypto.CryptoUtil;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import sn.pamecas.ordremission.ejb.entites.DemandeTDR;
import sn.pamecas.ordremission.ejb.entites.ValeurRessource;
import sn.pamecas.ordremission.ejb.entites.Vehicule;
import sn.pamecas.ordremission.ejb.outils.EJBRegistry;
import sn.pamecas.ordremission.ejb.outils.JNDIUtils;
import sn.pamecas.ordremission.ejb.session.DemandeTDRFacade;
import sn.pamecas.ordremission.ejb.session.VehiculeFacade;
import sn.pamecas.ordremission.web.outils.SendMail;
import sn.pamecas.ordremission.web.ressources.authentification.UserInfo;

public class OrdreMission {

	private DemandeTDR demande;
	private List<Vehicule> vehicule = new ArrayList<Vehicule>();
	private String signature;
	private String date;
	private Set<ValeurRessource> vaListe = new HashSet<ValeurRessource>();
	private String urlFichier;

	@SuppressWarnings({ "unchecked" })
	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Window comp) {

		Map<String, Object> params = (Map<String, Object>) Executions
				.getCurrent().getArg();
		demande = (DemandeTDR) params.get("demande");
		VehiculeFacade vf = (VehiculeFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.VehiculeFacade);
		vehicule = vf.findByDemande(demande);
		Date today = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
		String todayFormater = formater.format(today);
		date = todayFormater;
		vaListe = demande.getValeurRessources();
		urlFichier = demande.getFilePath();

	}

	public DemandeTDR getDemande() {
		return demande;
	}

	public void setDemande(DemandeTDR demande) {
		this.demande = demande;
	}

	public List<Vehicule> getVehicule() {
		return vehicule;
	}

	public void setVehicule(List<Vehicule> vehicule) {
		this.vehicule = vehicule;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Command
	@NotifyChange({"signature","demande"})
	public void signature(@ContextParam(ContextType.VIEW) Window comp) {
		Session s = Sessions.getCurrent();
		UserInfo<?> uf = (UserInfo<?>) s.getAttribute("userInfo");

		if (uf.getFonction().getLibelle().equalsIgnoreCase("DRH")) {
			Date today = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("ddMMyy");
			String todayFormater = formater.format(today);
			String c = encrypt(uf.getLogin());
			signature = c + todayFormater;
			demande.setSignatureDRH(signature);
			demande.setIsDRHAllowed(true);
			DemandeTDRFacade df = (DemandeTDRFacade) JNDIUtils
					.lookUpEJB(EJBRegistry.DemandeTDRFacade);
			df.edit(demande);
			Session session = Sessions.getCurrent();
			UserInfo<?> userInfo = (UserInfo<?>) session
					.getAttribute("userInfo");
			String from = "dareksar@gmail.com";
			String to = "dniang@pamecas.sn";
			String subject = "Validation ordre de mission";
			String text = "Vous avez reçu une demande d'ordre de mission."
					+ "\n \n Connectez vous sur l'application pour procéder à une validation.  "
					+ "\n http://localhost:8080/GesMission-WEB/index.zul";
			SendMail mailto = new SendMail();
			mailto.send(from, to, subject, text);

		} else {
			Date today = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("ddMMyy");
			String todayFormater = formater.format(today);
			String crypt = encrypt(uf.getLogin());
			signature = crypt + todayFormater;
			demande.setSignatureDGA(signature);
			demande.setIsDGAllowed(true);
			DemandeTDRFacade df = (DemandeTDRFacade) JNDIUtils
					.lookUpEJB(EJBRegistry.DemandeTDRFacade);
			df.edit(demande);
			Session session = Sessions.getCurrent();
			UserInfo<?> userInfo = (UserInfo<?>) session
					.getAttribute("userInfo");
			String from = "dniang@pamecas.sn";
			String to = "nmcamara@pamecas.sn";
			String subject = "Validation ordre de mission";
			String text = "Vous avez reçu une demande d'ordre de mission."
					+ "\n \n Connectez vous sur l'application pour procéder à une validation.  "
					+ "\n http://localhost:8080/GesMission-WEB/index.zul";
			//SendMail mailto = new SendMail();
			//mailto.send(from, to, subject, text);
			Messagebox.show("Ressource validée");

		}
	}

	@Command
	@NotifyChange({"signature","demande"})
	public void signatureParOrdre(@ContextParam(ContextType.VIEW) Window comp) {
		Session s = Sessions.getCurrent();
		UserInfo<?> uf = (UserInfo<?>) s.getAttribute("userInfo");

		if (uf.getFonction().getLibelle().equalsIgnoreCase("DRH")) {
			Date today = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("ddMMyy");
			String todayFormater = formater.format(today);
			String c = encrypt(uf.getLogin());
			signature = c + todayFormater;
			demande.setSignatureDRH(signature);
			demande.setIsDRHAllowed(true);
			DemandeTDRFacade df = (DemandeTDRFacade) JNDIUtils
					.lookUpEJB(EJBRegistry.DemandeTDRFacade);
			df.edit(demande);
			signature = "PO" + demande.getSignatureDRH();
			Session session = Sessions.getCurrent();
			UserInfo<?> userInfo = (UserInfo<?>) session
					.getAttribute("userInfo");
			String from = "dareksar@gmail.com";
			String to = "dniang@pamecas.sn";
			String subject = "Validation ordre de mission";
			String text = "Vous avez reçu une demande d'ordre de mission."
					+ "\n \n Connectez vous sur l'application pour procéder à une validation.  "
					+ "\n http://localhost:8080/GesMission-WEB/index.zul";
			//SendMail mailto = new SendMail();
			//mailto.send(from, to, subject, text);
			Messagebox.show("Ressource validée");
		} else {
			Date today = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("ddMMyy");
			String todayFormater = formater.format(today);
			String crypt = encrypt(uf.getLogin());
			signature = "PO" + crypt + todayFormater;
			demande.setSignatureDGA(signature);
			demande.setIsDGAllowed(true);
			DemandeTDRFacade df = (DemandeTDRFacade) JNDIUtils
					.lookUpEJB(EJBRegistry.DemandeTDRFacade);
			df.edit(demande);
			Session session = Sessions.getCurrent();
			UserInfo<?> userInfo = (UserInfo<?>) session
					.getAttribute("userInfo");
			String from = "dniang@pamecass.sn";
			String to = "nmcamara@pamecas.sn";
			String subject = "Validation ordre de mission";
			String text = "Vous avez reçu une demande d'ordre de mission."
					+ "\n \n Connectez vous sur l'application pour procéder à une validation.  "
					+ "\n http://localhost:8080/GesMission-WEB/index.zul";
			//SendMail mailto = new SendMail();
			//mailto.send(from, to, subject, text);
			

		}

	}

	public String encrypt(String login) {
		String crypte = "";
		for (int i = 0; i < login.length(); i++) {
			int c = login.charAt(i) ^ 48;
			crypte = crypte + (char) c;
		}
		return crypte;
	}

	public Set<ValeurRessource> getVaListe() {
		return vaListe;
	}

	public void setVaListe(Set<ValeurRessource> vaListe) {
		this.vaListe = vaListe;
	}

	public String getUrlFichier() {
		return urlFichier;
	}

	public void setUrlFichier(String urlFichier) {
		this.urlFichier = urlFichier;
	}
}
