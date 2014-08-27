package sn.pamecas.ordremission.web.demande.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import sn.pamecas.ordremission.ejb.entites.Accompagnant;
import sn.pamecas.ordremission.ejb.entites.DemandeTDR;
import sn.pamecas.ordremission.ejb.entites.Statut;
import sn.pamecas.ordremission.ejb.entites.Utilisateur;
import sn.pamecas.ordremission.ejb.entites.ValeurRessource;
import sn.pamecas.ordremission.ejb.outils.EJBRegistry;
import sn.pamecas.ordremission.ejb.outils.JNDIUtils;
import sn.pamecas.ordremission.ejb.session.DemandeTDRFacade;
import sn.pamecas.ordremission.ejb.session.StatutFacade;
import sn.pamecas.ordremission.web.outils.SendMail;
import sn.pamecas.ordremission.web.ressources.authentification.UserInfo;

public class AjouterControl {

	/**
	 * 
	 */

	private String txtObjet, txtLieu;

	private Date dateDeb, dateFin;
	private XSSFWorkbook xwb;
	private int nbrVehicule;
	private DemandeTDR demande = new DemandeTDR();

	private ValeurRessource selected;
	private Set<ValeurRessource> perdieumList = new HashSet<ValeurRessource>();
	private ListModelList<ValeurRessource> perdiemModel;
	private UserInfo<?> uf;
	private Set<Accompagnant> personnes = new HashSet<Accompagnant>();
	private List<Statut> statutList = new ArrayList<Statut>();
	private Statut statutSelected;

	private List<ValeurRessource> lstVA = new ArrayList<ValeurRessource>();

	private java.io.InputStream input;

	@AfterCompose
	public void init() {

		// ValeurRessourceFacade vrService = (ValeurRessourceFacade)
		// JNDIUtils.lookUpEJB(EJBRegistry.ValeurRessourceFacade);
		Session session = Sessions.getCurrent();
		uf = (UserInfo<?>) session.getAttribute("userInfo");
		// perdieumList = uf.getStatut().getPerdiem().getValeurRessources();
		// perdiemModel = new ListModelList<ValeurRessource>(perdieumList);
		// perdiemModel.setMultiple(true);
		StatutFacade statutFacade = (StatutFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.StatutFacade);
		statutList = statutFacade.findAll();
	}

	public ValeurRessource getSelected() {
		return selected;
	}

	public void setSelected(ValeurRessource selected) {
		this.selected = selected;
	}

	@Command
	@NotifyChange({ "personnes", "statutList", "statutSelected" })
	public void ajouterPers() {
		personnes.add(new Accompagnant());
	}

	@Listen("onListeMission = window")
	public void saveList(Event event) {
		lstVA = (List<ValeurRessource>) event.getData();
	}

	@Command
	public void ajouterFrais(
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx,
			@ContextParam(ContextType.VIEW) Window comp) throws Exception {
		UploadEvent evt = (UploadEvent) ctx.getTriggerEvent();

		if (evt.getMedia().getFormat().equalsIgnoreCase("xlsx")) {
			xwb = new XSSFWorkbook(evt.getMedia().getStreamData());
			input = evt.getMedia().getStreamData();
			Messagebox.show("done!");

		}

	}

	@Command
	public void vehiculeOK() {
		demande.setIsVehicule(true);
	}

	@Command
	@NotifyChange({ "personnes", "statutList", "perdiemModel" })
	public void save(@ContextParam(ContextType.VIEW) Window comp,
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx)
			throws Exception {
		String name = String
				.format("frais-%d.xlsx", System.currentTimeMillis());
		File file = new File(comp.getDesktop().getWebApp().getServletContext()
				.getContextPath()
				+ name);

		FileOutputStream outPut = new FileOutputStream(file);
		xwb.write(outPut);
		// Filedownload.save(input, "application/file", "/WEB-INF/upload/" +
		// name);
		outPut.close();
		demande.setFilePath(comp.getDesktop().getWebApp().getServletContext()
				.getContextPath()
				+ name);
		demande.setLieu(txtLieu);
		demande.setObjet(txtObjet);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dateDebut = format.format(dateDeb);
		demande.setDateDeb(dateDebut);
		String dateF = format.format(dateFin);
		demande.setDateFin(dateF);
		// demande.setNbrVehicule(nbrVehicule);
		demande.setIsAllowed(false);
		demande.setIsDeleted(false);
		demande.setIsComptaAllowed(false);
		demande.setIsDRHAllowed(false);
		demande.setIsDGAllowed(false);
		demande.setIsPatAllowed(false);
		demande.setIsSuperviseurAllowed(false);
		demande.setIsControleurAllowed(false);
		Utilisateur u = new Utilisateur();
		u.setId(uf.getId());
		// demande.setRessource(txtRessource.getValue());
		demande.setUtilisateur(u);
		demande.setAccompagnants(personnes);
		demande.setNbrPersonne(personnes.size() + 1);
		DemandeTDRFacade demandeTDRFacade = (DemandeTDRFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.DemandeTDRFacade);
		demandeTDRFacade.edit(demande);

		Events.postEvent("onListAdd", comp.getParent(), demande);
		String from = "dareksar@gmail.com";
		String to = "nmcamara@pamecas.sn";
		String subject = "Demande d'ordre de mission";
		String text = "Vous avez reçu une demande d'ordre de mission."
				+ "\n \n Connectez vous sur l'application pour procéder à une validation.  "
				+ "\n http://localhost:8080/GesMission-WEB/index.zul";
		SendMail mailto = new SendMail();
		mailto.send(from, to, subject, text);
		Messagebox.show("Demande enregistree");

		comp.detach();
	}

	public String getTxtObjet() {
		return txtObjet;
	}

	public void setTxtObjet(String txtObjet) {
		this.txtObjet = txtObjet;
	}

	public String getTxtLieu() {
		return txtLieu;
	}

	public void setTxtLieu(String txtLieu) {
		this.txtLieu = txtLieu;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getNbrVehicule() {
		return nbrVehicule;
	}

	public void setNbrVehicule(int nbrVehicule) {
		this.nbrVehicule = nbrVehicule;
	}

	public Set<Accompagnant> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(Set<Accompagnant> personnes) {
		this.personnes = personnes;
	}

	public List<Statut> getStatutList() {
		return statutList;
	}

	public void setStatutList(List<Statut> statutList) {
		this.statutList = statutList;
	}

	public Statut getStatutSelected() {
		return statutSelected;
	}

	public void setStatutSelected(Statut statutSelected) {
		this.statutSelected = statutSelected;
	}

	public ListModelList<ValeurRessource> getPerdiemModel() {
		return perdiemModel;
	}

	public void setPerdiemModel(ListModelList<ValeurRessource> perdiemModel) {
		this.perdiemModel = perdiemModel;
	}

	public Set<ValeurRessource> getPerdieumList() {
		return perdieumList;
	}

	public void setPerdieumList(Set<ValeurRessource> perdieumList) {
		this.perdieumList = perdieumList;
	}

}
