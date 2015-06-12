package sn.maadji.healthcare.web.listepatientdomicile;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Space;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zul.Window.Mode;

import sn.maadji.healthcare.ejb.entite.Fonctionnalite;
import sn.maadji.healthcare.web.authentification.UserInfo;

public class ListePatientDomicileControl extends SelectorComposer<Component> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Toolbarbutton dossierSoin, dossierAdministratif, dossierMedical;
	@Wire 
	private Hbox toolbarBtn;
	String idSoin = "dossierSoin";
	

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		UserInfo<?> userInfo = (UserInfo<?>) Sessions.getCurrent()
				.getAttribute("userInfo");
		for(final Fonctionnalite f : userInfo.getProfil().getFonctionnalites()){
			Toolbarbutton tb = new Toolbarbutton();
			tb.setLabel(f.getName());
			Space space = new Space();
			space.setParent(toolbarBtn);
			tb.setParent(toolbarBtn);
			tb.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					if(f.getName().equalsIgnoreCase("dossier soin")){
						afficherDossierSoin();
					}
					if(f.getName().equalsIgnoreCase("dossier administratif")){
						afficherAdministratiff();
					}
					if(f.getName().equalsIgnoreCase("dossier medical")){
						afficherMedical();
					}
					if(f.getName().equalsIgnoreCase("profil patient")){
						afficherprofilPatient();
					}
					if(f.getName().equalsIgnoreCase("Nouveau")){
						afficherNouveau();
					}
				}

				
			});
			
		}
		
	}

	//@Listen("onClick=#dossierSoin")
	public void afficherDossierSoin() {
		Window win = (Window) Executions.createComponents(
				"/pages/page_soins/creer_dossier_soin.zul", getSelf(), null);
		win.setMode(Mode.MODAL);

	}

	//@Listen("onClick=#dossierMedical")
	public void afficherMedical() {
		Window win = (Window) Executions.createComponents(
				"/pages/page_medical/details_dossier_medical.zul", getSelf(),
				null);
		win.setMode(Mode.MODAL);

	}

	//@Listen("onClick=#dossierAdministratif")
	public void afficherAdministratiff() {
		Window win = (Window) Executions.createComponents(
				"/pages/page_administratif/dossier_administratif.zul",
				getSelf(), null);
		win.setMode(Mode.MODAL);

	}

	//@Listen("onClick=#profilPatient")
	public void afficherprofilPatient() {
		Window win = (Window) Executions.createComponents(
				"/pages/page_patient/details_patient.zul", getSelf(), null);
		win.setMode(Mode.MODAL);

	}
	private void afficherNouveau() {
		Window win = (Window) Executions.createComponents(
				"/pages/page_patient/creer_patient.zul", getSelf(), null);
		win.setMode(Mode.MODAL);
		
	}

}
