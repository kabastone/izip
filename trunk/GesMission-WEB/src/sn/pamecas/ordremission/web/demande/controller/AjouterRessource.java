package sn.pamecas.ordremission.web.demande.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;

import sn.pamecas.ordremission.ejb.entites.TypeRessource;
import sn.pamecas.ordremission.ejb.entites.ValeurRessource;
import sn.pamecas.ordremission.ejb.outils.EJBRegistry;
import sn.pamecas.ordremission.ejb.outils.JNDIUtils;

public class AjouterRessource {

	
	private List<ValeurRessource> valeurRessources = new ArrayList<ValeurRessource>();

	
	public List<ValeurRessource> getValeurRessources() {
		return valeurRessources;
	}

	public void setValeurRessources(List<ValeurRessource> valeurRessources) {
		this.valeurRessources = valeurRessources;
	}

	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Window comp) {
		Map<String, Object> param = (Map<String, Object>) Executions
				.getCurrent().getArg();
		//typeRessource = (TypeRessource) param.get("typeRessource");
	}

	@Command
	@NotifyChange("valeurRessources")
	public void ajouterPStr() {
		valeurRessources.add(new ValeurRessource());

	}

	@Command
	@NotifyChange("listParamRes")
	public void save(@ContextParam(ContextType.VIEW) Window comp) {
		Events.postEvent("onRessourceAdd", comp.getParent(), valeurRessources);
     comp.detach();
	}
}