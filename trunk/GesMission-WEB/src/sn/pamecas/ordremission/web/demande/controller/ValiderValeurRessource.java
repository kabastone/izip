package sn.pamecas.ordremission.web.demande.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import sn.pamecas.ordremission.ejb.entites.DemandeTDR;
import sn.pamecas.ordremission.ejb.entites.Depense;
import sn.pamecas.ordremission.ejb.entites.OrdreDecaissement;
import sn.pamecas.ordremission.ejb.entites.ValeurRessource;
import sn.pamecas.ordremission.ejb.outils.EJBRegistry;
import sn.pamecas.ordremission.ejb.outils.JNDIUtils;
import sn.pamecas.ordremission.ejb.session.DemandeTDRFacade;
import sn.pamecas.ordremission.ejb.session.DepenseFacade;
import sn.pamecas.ordremission.ejb.session.OrdreDecaissementFacade;
import sn.pamecas.ordremission.web.outils.SendMail;
import sn.pamecas.ordremission.web.ressources.authentification.UserInfo;

public class ValiderValeurRessource {
	private DemandeTDR demande;
	private Set<ValeurRessource> vrListe = new HashSet<ValeurRessource>();
	private String nbrPersonne;
	private Float total = 0F;
	private String urlFichier;
	private String serviceA;
	private String date;
	private String serviceD;
	private String description;
	private Float montant;
	private String budget;
	private ListModelList<Depense> depenseModel = new ListModelList<Depense>();
	private DepenseFacade depenseFacade = (DepenseFacade) JNDIUtils
			.lookUpEJB(EJBRegistry.DepenseFacade);

	@SuppressWarnings("unchecked")
	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Window comp) {

		Map<String, Object> params = (Map<String, Object>) Executions
				.getCurrent().getArg();
		demande = (DemandeTDR) params.get("demande");
		vrListe = demande.getValeurRessources();
		urlFichier = demande.getFilePath();

		depenseModel = new ListModelList<Depense>(depenseFacade.findAll());
		depenseModel.setMultiple(true);

	}

	public Set<ValeurRessource> getVrListe() {
		return vrListe;
	}

	public void setVrListe(Set<ValeurRessource> vrListe) {
		this.vrListe = vrListe;
	}

	public String getNbrPersonne() {
		return nbrPersonne;
	}

	public void setNbrPersonne(String nbrPersonne) {
		this.nbrPersonne = nbrPersonne;
	}

	@Command
	@NotifyChange({ "vrListe", "total" })
	public void afficherTotal() {
		total = 0f;
		Float resultat = 0f;
		for (ValeurRessource vr : vrListe) {
			resultat += vr.getValeur();
			total = resultat * demande.getNbrPersonne();
		}
	}

	@Command
	@NotifyChange({ "vrListe", "total" })
	public void validerVR(@ContextParam(ContextType.VIEW) Window comp) {
		DemandeTDRFacade df = (DemandeTDRFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.DemandeTDRFacade);
		OrdreDecaissementFacade odf = (OrdreDecaissementFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.OrdreDecaissementFacade);
		OrdreDecaissement ordre = new OrdreDecaissement();
		ordre.setServiceA(serviceA);
		ordre.setServiceD(serviceD);
		ordre.setDescription(description);
		ordre.setMontant(montant);
		ordre.setBudget(budget);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dateFormat = format.format(new Date());
		ordre.setDate(dateFormat);
		ordre.setDepenses(depenseModel.getSelection());
		odf.edit(ordre);
		demande.setIsControleurAllowed(true);
		demande.setOrdreDecaissement(ordre);
		df.edit(demande);
		Session session = Sessions.getCurrent();
		UserInfo<?> userInfo = (UserInfo<?>) session.getAttribute("userInfo");
		String from = "dareksar@gmail.com";
		String to = "nmcamara@pamecas.sn";
		String subject = "Validation ordre de mission";
		String text = "Vous avez reçu une demande d'ordre de mission."
				+ "\n \n Connectez vous sur l'application pour procéder à une validation.  "
				+ "\n http://localhost:8080/GesMission-WEB/index.zul";
		SendMail mailto = new SendMail();
		mailto.send(from, to, subject, text);
		Messagebox.show("Ressource validée");
		Messagebox.show("Ressource validé");
		comp.detach();

	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public String getUrlFichier() {
		return urlFichier;
	}

	public void setUrlFichier(String urlFichier) {
		this.urlFichier = urlFichier;
	}

	public String getServiceA() {
		return serviceA;
	}

	public void setServiceA(String serviceA) {
		this.serviceA = serviceA;
	}

	public String getServiceD() {
		return serviceD;
	}

	public void setServiceD(String serviceD) {
		this.serviceD = serviceD;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getMontant() {
		return montant;
	}

	public void setMontant(Float montant) {
		this.montant = montant;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public ListModelList<Depense> getDepenseModel() {
		return depenseModel;
	}

	public void setDepenseModel(ListModelList<Depense> depenseModel) {
		this.depenseModel = depenseModel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
