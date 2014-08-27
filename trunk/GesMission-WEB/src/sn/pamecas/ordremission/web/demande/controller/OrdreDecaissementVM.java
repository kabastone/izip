package sn.pamecas.ordremission.web.demande.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

import sn.pamecas.ordremission.ejb.entites.DemandeTDR;
import sn.pamecas.ordremission.ejb.entites.Depense;
import sn.pamecas.ordremission.ejb.entites.OrdreDecaissement;
import sn.pamecas.ordremission.ejb.outils.EJBRegistry;
import sn.pamecas.ordremission.ejb.outils.JNDIUtils;
import sn.pamecas.ordremission.ejb.session.DepenseFacade;
import sn.pamecas.ordremission.web.outils.ConvertirChiffreEnLettre;

public class OrdreDecaissementVM {
	private OrdreDecaissement ordre = new OrdreDecaissement();
	private List<Depense> depenses = new ArrayList<Depense>();
	private String montantConvertit;

	@SuppressWarnings("unchecked")
	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Window comp) {
		Map<String, Object> params = (Map<String, Object>) Executions
				.getCurrent().getArg();
		DemandeTDR d = (DemandeTDR) params.get("demande");
		ordre = d.getOrdreDecaissement();
		DepenseFacade depenseFacade = (DepenseFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.DepenseFacade);
		//depenses = depenseFacade.findByOrdre(ordre);
		
         montantConvertit ="(" +ConvertirChiffreEnLettre.convert(ordre.getMontant().longValue())+")";
	}

	public OrdreDecaissement getOrdre() {
		return ordre;
	}

	public void setOrdre(OrdreDecaissement ordre) {
		this.ordre = ordre;
	}

	public List<Depense> getDepenses() {
		return depenses;
	}

	public void setDepenses(List<Depense> depenses) {
		this.depenses = depenses;
	}

	public String getMontantConvertit() {
		return montantConvertit;
	}

	public void setMontantConvertit(String montantConvertit) {
		this.montantConvertit = montantConvertit;
	}

}
