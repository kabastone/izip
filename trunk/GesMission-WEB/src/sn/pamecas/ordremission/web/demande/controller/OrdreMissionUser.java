package sn.pamecas.ordremission.web.demande.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import sn.pamecas.ordremission.ejb.entites.DemandeTDR;
import sn.pamecas.ordremission.ejb.entites.Vehicule;
import sn.pamecas.ordremission.ejb.outils.EJBRegistry;
import sn.pamecas.ordremission.ejb.outils.JNDIUtils;
import sn.pamecas.ordremission.ejb.session.DemandeTDRFacade;
import sn.pamecas.ordremission.ejb.session.VehiculeFacade;

public class OrdreMissionUser {
	
	private DemandeTDR demande;
	private List<Vehicule> vehicule = new ArrayList<Vehicule>();
	private String signature;
	private String date;
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
	public void valider(@ContextParam(ContextType.VIEW) Window comp) {
		demande.setIsComptaAllowed(true);
		demande.setIsAllowed(true);
		DemandeTDRFacade df = (DemandeTDRFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.DemandeTDRFacade);
		df.edit(demande);
		Messagebox.show("Ordre de mission Validé");
		//TODO envoyer e-mail
		comp.detach();

	}

	@Command
	public void annuler(@ContextParam(ContextType.VIEW) Window comp) {
		// TODO envoyer e-mail
	}

	public String encrypt(String login) {
		String crypte = "";
		for (int i = 0; i < login.length(); i++) {
			int c = login.charAt(i) ^ 48;
			crypte = crypte + (char) c;
		}
		return crypte;
	}


}
