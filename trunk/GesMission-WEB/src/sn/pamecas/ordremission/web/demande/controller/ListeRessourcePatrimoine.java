package sn.pamecas.ordremission.web.demande.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import sn.pamecas.ordremission.ejb.entites.DemandeTDR;
import sn.pamecas.ordremission.ejb.entites.ValeurRessource;
import sn.pamecas.ordremission.ejb.entites.Vehicule;
import sn.pamecas.ordremission.ejb.outils.EJBRegistry;
import sn.pamecas.ordremission.ejb.outils.JNDIUtils;
import sn.pamecas.ordremission.ejb.session.ValeurRessourceFacade;
import sn.pamecas.ordremission.ejb.session.VehiculeFacade;

public class ListeRessourcePatrimoine {
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

		if (demande.getIsVehicule()) {
           VehiculeFacade vf = (VehiculeFacade) JNDIUtils.lookUpEJB(EJBRegistry.VehiculeFacade);
           
			vehiculeListe = vf.findByDemande(demande);
			vehiculeModel = new ListModelList<Vehicule>(vehiculeListe);
		}
	}

	public ListModelList<Vehicule> getVehiculeModel() {
		return vehiculeModel;
	}

	public void setVehiculeModel(ListModelList<Vehicule> vehiculeModel) {
		this.vehiculeModel = vehiculeModel;
	}

}
