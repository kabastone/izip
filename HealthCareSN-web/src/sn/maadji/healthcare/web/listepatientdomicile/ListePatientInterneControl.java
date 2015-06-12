package sn.maadji.healthcare.web.listepatientdomicile;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.Window.Mode;

public class ListePatientInterneControl extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	public Toolbarbutton dossiersoins, dossieradmin, dossiermed;

	@Listen("onClick=#dossiersoins")
	public void afficherdossiersoins() {

		Window win = (Window) Executions.createComponents(
				"/pages/page_soins/creer_dossier_soin.zul", getSelf(), null);
		win.setMode(Mode.MODAL);
	}

	@Listen("onClick=#dossiermed")
	public void afficherdossiermed() {

		Window win = (Window) Executions.createComponents(
				"/pages/page_medical/details_dossier_medical.zul", getSelf(),
				null);
		win.setMode(Mode.MODAL);
	}

	@Listen("onClick=#dossieradmin")
	public void afficherdossieradmin() {

		Window win = (Window) Executions.createComponents(
				"/pages/page_administratif/dossier_administratif.zul",
				getSelf(), null);
		win.setMode(Mode.MODAL);
	}

	@Listen("onClick=#profilPatient")
	public void afficherprofilPatient() {

		Window win = (Window) Executions.createComponents(
				"/pages/page_patient/details_patient.zul", getSelf(), null);
		win.setMode(Mode.MODAL);
	}

}
