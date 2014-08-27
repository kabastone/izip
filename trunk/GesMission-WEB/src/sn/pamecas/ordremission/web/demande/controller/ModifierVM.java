package sn.pamecas.ordremission.web.demande.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import sn.pamecas.ordremission.ejb.entites.DemandeTDR;
import sn.pamecas.ordremission.ejb.entites.Utilisateur;
import sn.pamecas.ordremission.ejb.outils.EJBRegistry;
import sn.pamecas.ordremission.ejb.outils.JNDIUtils;
import sn.pamecas.ordremission.ejb.session.DemandeTDRFacade;
import sn.pamecas.ordremission.web.outils.SendMail;
import sn.pamecas.ordremission.web.ressources.authentification.UserInfo;

public class ModifierVM {
	private String txtObjet, txtLieu;

	private String dateDeb, dateFin;
	private XSSFWorkbook xwb = null;
	private int nbrVehicule;
	private DemandeTDR demande = new DemandeTDR();
	private UserInfo<?> uf;

	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Window comp) {

		// ValeurRessourceFacade vrService = (ValeurRessourceFacade)
		// JNDIUtils.lookUpEJB(EJBRegistry.ValeurRessourceFacade);
		Session session = Sessions.getCurrent();
		uf = (UserInfo<?>) session.getAttribute("userInfo");
		// perdieumList = uf.getStatut().getPerdiem().getValeurRessources();
		// perdiemModel = new ListModelList<ValeurRessource>(perdieumList);
		// perdiemModel.setMultiple(true);
		// StatutFacade statutFacade = (StatutFacade) JNDIUtils
		// .lookUpEJB(EJBRegistry.StatutFacade);
		// statutList = statutFacade.findAll();
		Map<String, Object> params = new HashMap<String, Object>();
		params = (Map<String, Object>) Executions.getCurrent().getArg();
		demande = (DemandeTDR) params.get("demande");
		txtLieu = demande.getLieu();
		txtObjet = demande.getObjet();
		dateDeb = demande.getDateDeb();
		dateFin = demande.getDateFin();
		
		
	}

	@Command
	public void ajouterFrais(
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx,
			@ContextParam(ContextType.VIEW) Window comp) throws Exception {
		UploadEvent evt = (UploadEvent) ctx.getTriggerEvent();

		if (evt.getMedia().getFormat().equalsIgnoreCase("xlsx")) {
			xwb = new XSSFWorkbook(evt.getMedia().getStreamData());
			// input = evt.getMedia().getStreamData();
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
		if(xwb != null){
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
		}
		demande.setLieu(txtLieu);
		demande.setObjet(txtObjet);
		//SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		//String dateDebut = format.format(dateDeb);
		demande.setDateDeb(dateDeb);
		//String dateF = format.format(dateFin);
		demande.setDateFin(dateFin);
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
		// demande.setAccompagnants(personnes);
		// demande.setNbrPersonne(personnes.size() + 1);
		DemandeTDRFacade demandeTDRFacade = (DemandeTDRFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.DemandeTDRFacade);
		demandeTDRFacade.edit(demande);

		Events.postEvent("onListChange", comp.getParent(), demande);
		/*String from = "dareksar@gmail.com";
		String to = "nmcamara@pamecas.sn";
		String subject = "Demande d'ordre de mission";
		String text = "Vous avez reçu une demande d'ordre de mission."
				+ "\n \n Connectez vous sur l'application pour procéder à une validation.  "
				+ "\n http://localhost:8080/GesMission-WEB/index.zul";
		SendMail mailto = new SendMail();
		mailto.send(from, to, subject, text);*/
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

	public String getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(String dateDeb) {
		this.dateDeb = dateDeb;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public int getNbrVehicule() {
		return nbrVehicule;
	}

	public void setNbrVehicule(int nbrVehicule) {
		this.nbrVehicule = nbrVehicule;
	}

}
