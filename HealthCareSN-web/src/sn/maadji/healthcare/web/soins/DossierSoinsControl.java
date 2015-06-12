package sn.maadji.healthcare.web.soins;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.Window.Mode;

public class DossierSoinsControl extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	public Toolbarbutton evaluation, detailsoin, elimination, medication,
			nouveau;

	@Listen("onClick=#evaluations")
	public void afficherEvaluation() {
		Include include = (Include) Selectors
				.iterable(getPage(), "#mainInclude").iterator().next();
		include.setSrc("/pages/evaluation/creer_evaluation.zul");
	}

	@Listen("onClick=#eliminations")
	public void afficherElimination() {
		Include include = (Include) Selectors
				.iterable(getPage(), "#mainInclude").iterator().next();
		include.setSrc("/pages/elimination/creer_elimination.zul");
	}

	@Listen("onClick=#medications")
	public void afficherMedication() {
		Include include = (Include) Selectors
				.iterable(getPage(), "#mainInclude").iterator().next();
		include.setSrc("/pages/medication/creer_medication.zul");
	}

	@Listen("onClick=#detailsoins")
	public void afficherDetailsoins() {
		Include include = (Include) Selectors
				.iterable(getPage(), "#mainInclude").iterator().next();
		include.setSrc("/pages/details_soin/creer_detailsoin.zul");
	}

	@Listen("onClick=#nouveau")
	public void afficherNouveau() {
		Include include = (Include) Selectors
				.iterable(getPage(), "#mainInclude").iterator().next();
		include.setSrc("/pages/page_soins/voir_details_soin.zul");

	}

	@Listen("onClick=#voirDetails")
	public void afficherVoirDetails() {

		Include include = (Include) Selectors
				.iterable(getPage(), "#mainInclude").iterator().next();
		include.setSrc("/pages/page_soins/vue_dossier_soins.zul");

	}
}
