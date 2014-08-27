package sn.pamecas.ordremission.web.demande.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.AbstractComponent;
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
import sn.pamecas.ordremission.ejb.session.ValeurRessourceFacade;
import sn.pamecas.ordremission.ejb.session.VehiculeFacade;
import sn.pamecas.ordremission.web.outils.SendMail;
import sn.pamecas.ordremission.web.ressources.authentification.UserInfo;

public class ValiderRessourcePatrimoine {

	/**
	 * 
	 */

	private String nbrVehicule;
	private DemandeTDR demande = new DemandeTDR();
	private ListModelList<Vehicule> vehiculeModel;
	private List<Vehicule> vehiculeListe = new ArrayList<Vehicule>();
	ValeurRessourceFacade vrService = (ValeurRessourceFacade) JNDIUtils
			.lookUpEJB(EJBRegistry.ValeurRessourceFacade);
	ListModelList<ValeurRessource> listModel;
	


	@SuppressWarnings("unchecked")
	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Window comp) {

		Map<String, Object> params = (Map<String, Object>) Executions
				.getCurrent().getArg();
		demande = (DemandeTDR) params.get("demande");

		if(demande.getIsVehicule()){
			
			vehiculeListe.add(new Vehicule());
		}
	}

	@Command
	@NotifyChange("vehiculeListe")
	public void ajouterVL() {
		vehiculeListe.add(new Vehicule());
	}

	public ListModelList<ValeurRessource> getListModel() {
		return listModel;
	}

	public void setListModel(ListModelList<ValeurRessource> listModel) {
		this.listModel = listModel;
	}

	@Command
	@NotifyChange("vehiculeListe")
	public void validerVL(@ContextParam(ContextType.VIEW) Window comp) {
		try {

			VehiculeFacade vf = (VehiculeFacade) JNDIUtils
					.lookUpEJB(EJBRegistry.VehiculeFacade);
			for (Vehicule v : vehiculeListe) {

				v.setDemande(demande);
				vf.edit(v);
			}
			demande.setIsPatAllowed(true);
			DemandeTDRFacade df = (DemandeTDRFacade) JNDIUtils
					.lookUpEJB(EJBRegistry.DemandeTDRFacade);
			df.edit(demande);
			 
			Session session = Sessions.getCurrent();
	         UserInfo<?> userInfo =(UserInfo<?>) session .getAttribute("userInfo");
			String from = "nmcamara@pamecas.sn";
			String to = "dniang@pamecas.sn";
			String subject = "Validation ordre de mission";
			String text = "Vous avez reçu une demande d'ordre de mission."
					+ "\n \n Connectez vous sur l'application pour procéder à une validation.  "
					+ "\n http://localhost:8080/GesMission-WEB/index.zul";
			SendMail mailto = new SendMail();
			mailto.send(from, to, subject, text);
			Messagebox.show("Ressource validée");
			comp.detach();
		} catch (Exception e) {
			Messagebox.show("Echec validation !");
			e.printStackTrace();
		}
	}

	public ListModelList<Vehicule> getVehiculeModel() {
		return vehiculeModel;
	}

	public void setVehiculeModel(ListModelList<Vehicule> vehiculeModel) {
		this.vehiculeModel = vehiculeModel;
	}

	public List<Vehicule> getVehiculeListe() {
		return vehiculeListe;
	}

	public void setVehiculeListe(List<Vehicule> vehiculeListe) {
		this.vehiculeListe = vehiculeListe;
	}

	public String getNbrVehicule() {
		return nbrVehicule;
	}

	public void setNbrVehicule(String nbrVehicule) {
		this.nbrVehicule = nbrVehicule;
	}
}
