package sn.pamecas.ordremission.web.demande.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Window;
import org.zkoss.zul.Window.Mode;

import sn.pamecas.ordremission.ejb.entites.DemandeTDR;
import sn.pamecas.ordremission.ejb.entites.ValeurRessource;
import sn.pamecas.ordremission.ejb.entites.Vehicule;
import sn.pamecas.ordremission.ejb.outils.EJBRegistry;
import sn.pamecas.ordremission.ejb.outils.JNDIUtils;
import sn.pamecas.ordremission.ejb.session.DemandeTDRFacade;
import sn.pamecas.ordremission.ejb.session.VehiculeFacade;
import sn.pamecas.ordremission.web.ressources.authentification.UserInfo;

public class DetailsRessource {
	private Set<ValeurRessource> vrListe = new HashSet<ValeurRessource>();
	private List<Vehicule> vehiculeListe = new ArrayList<Vehicule>();
	private DemandeTDR demande = new DemandeTDR();
	private String nbrPersonne;
	private String urlFichier;

	@SuppressWarnings("unchecked")
	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Window comp) {
		VehiculeFacade vf = (VehiculeFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.VehiculeFacade);

		Map<String, Object> params = (Map<String, Object>) Executions
				.getCurrent().getArg();
		demande = (DemandeTDR) params.get("demande");
		vrListe = demande.getValeurRessources();
		vehiculeListe = vf.findByDemande(demande);
		nbrPersonne = demande.getNbrPersonne() + "";
		urlFichier = demande.getFilePath();
	}

	@Command
	public void genererOM(@ContextParam(ContextType.VIEW) Window comp) {
		Session s = Sessions.getCurrent();
		UserInfo<?> uf = (UserInfo<?>) s.getAttribute("userInfo");
		Date today = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("ddMMyy");
		String todayFormater = formater.format(today);
        String c = encrypt(uf.getLogin());
		String signature = c + todayFormater;
		demande.setSignatureDRH(signature);
		demande.setIsDRHAllowed(true);
		DemandeTDRFacade df = (DemandeTDRFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.DemandeTDRFacade);
		df.edit(demande);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("demande", demande);
		Window window = (Window) Executions.createComponents(
				"/pages/demande/ordreMission.zul", comp.getParent(), params);
		window.setMode(Mode.MODAL);
		comp.detach();

	}

	public Set<ValeurRessource> getVrListe() {
		return vrListe;
	}

	public void setVrListe(Set<ValeurRessource> vrListe) {
		this.vrListe = vrListe;
	}

	public List<Vehicule> getVehiculeListe() {
		return vehiculeListe;
	}

	public void setVehiculeListe(List<Vehicule> vehiculeListe) {
		this.vehiculeListe = vehiculeListe;
	}

	public String getNbrPersonne() {
		return nbrPersonne;
	}

	public void setNbrPersonne(String nbrPersonne) {
		this.nbrPersonne = nbrPersonne;
	}

	public String encrypt(String login) {
		String crypte = "";
		for (int i = 0; i < login.length(); i++) {
			int c = login.charAt(i)^48;
			crypte = crypte + (char) c;
		}
		return crypte;
	}

	public String getUrlFichier() {
		return urlFichier;
	}

	public void setUrlFichier(String urlFichier) {
		this.urlFichier = urlFichier;
	}
}
