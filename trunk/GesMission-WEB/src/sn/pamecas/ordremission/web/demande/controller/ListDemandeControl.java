package sn.pamecas.ordremission.web.demande.controller;

import java.util.HashMap;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox.Button;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Window;
import org.zkoss.zul.Window.Mode;

import sn.pamecas.ordremission.ejb.entites.DemandeTDR;
import sn.pamecas.ordremission.ejb.outils.EJBRegistry;
import sn.pamecas.ordremission.ejb.outils.JNDIUtils;
import sn.pamecas.ordremission.ejb.session.DemandeTDRFacade;
import sn.pamecas.ordremission.web.components.ActionButton;
import sn.pamecas.ordremission.web.ressources.authentification.UserInfo;

public class ListDemandeControl extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ON_CLICK_ACTION_BUTTON = "onClickActionButton";
	@Wire
	private Listbox listDemande;
	@Wire
	private ActionButton ajouter, modifier, supprimer, voir;
	private ListModelList<DemandeTDR> demandeModel = new ListModelList<DemandeTDR>();
	private Session s = Sessions.getCurrent();
	private UserInfo<?> uf = (UserInfo<?>) s.getAttribute("userInfo");

	@Listen("onCreate=window")
	public void list() throws Exception {

		if (uf != null) {
			try {
				if (uf.getFonction().getLibelle()
						.equalsIgnoreCase("Controleur")) {
					ajouter.detach();
					modifier.detach();
					supprimer.detach();
					DemandeTDRFacade demandeService = (DemandeTDRFacade) JNDIUtils
							.lookUpEJB(EJBRegistry.DemandeTDRFacade);
					demandeModel = new ListModelList<DemandeTDR>(
							demandeService.displayTDRControleur());
					listDemande.setModel(demandeModel);

				} else if (uf.getFonction().getLibelle()
						.equalsIgnoreCase("Comptable")) {
					ajouter.detach();
					modifier.detach();
					supprimer.detach();

					DemandeTDRFacade demandeService = (DemandeTDRFacade) JNDIUtils
							.lookUpEJB(EJBRegistry.DemandeTDRFacade);
					demandeModel = new ListModelList<DemandeTDR>(
							demandeService.displayTDRComptable());
					listDemande.setModel(demandeModel);

				} else if (uf.getFonction().getLibelle()
						.equalsIgnoreCase("Patrimoine")) {
					ajouter.detach();
					modifier.detach();
					supprimer.detach();
					DemandeTDRFacade demandeService = (DemandeTDRFacade) JNDIUtils
							.lookUpEJB(EJBRegistry.DemandeTDRFacade);
					demandeModel = new ListModelList<DemandeTDR>(
							demandeService.displayTDRPatrimoine());
					listDemande.setModel(demandeModel);
				} else if (uf.getFonction().getLibelle()
						.equalsIgnoreCase("Superviseur")) {
					ajouter.detach();
					modifier.detach();
					supprimer.detach();
					DemandeTDRFacade demandeService = (DemandeTDRFacade) JNDIUtils
							.lookUpEJB(EJBRegistry.DemandeTDRFacade);
					demandeModel = new ListModelList<DemandeTDR>(
							demandeService.displayTDRSuperviseur());
					listDemande.setModel(demandeModel);
				}

				else if (uf.getFonction().getLibelle().equalsIgnoreCase("DRH")) {
					ajouter.detach();
					modifier.detach();
					supprimer.detach();
					DemandeTDRFacade demandeService = (DemandeTDRFacade) JNDIUtils
							.lookUpEJB(EJBRegistry.DemandeTDRFacade);
					demandeModel = new ListModelList<DemandeTDR>(
							demandeService.displaytdrDRH());
					listDemande.setModel(demandeModel);
				} else if (uf.getFonction().getLibelle()
						.equalsIgnoreCase("DGA")) {
					ajouter.detach();
					modifier.detach();
					supprimer.detach();
					DemandeTDRFacade demandeService = (DemandeTDRFacade) JNDIUtils
							.lookUpEJB(EJBRegistry.DemandeTDRFacade);
					demandeModel = new ListModelList<DemandeTDR>(
							demandeService.displaytdrDGA());
					listDemande.setModel(demandeModel);
				} else {

					DemandeTDRFacade demandeService = (DemandeTDRFacade) JNDIUtils
							.lookUpEJB(EJBRegistry.DemandeTDRFacade);
					demandeModel = new ListModelList<DemandeTDR>(
							demandeService.displayTDR(uf.getId()));
					listDemande.setModel(demandeModel);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}

	@Listen("onListChange = window")
	public void refreshList(Event event) {
		DemandeTDR demande = (DemandeTDR) event.getData();
		((ListModelList<DemandeTDR>) demandeModel).set(
				listDemande.getSelectedIndex(), demande);
	}

	@Listen("onListAdd = window")
	public void refreshList1(Event event) {
		DemandeTDR demande = (DemandeTDR) event.getData();
		((ListModelList<DemandeTDR>) demandeModel).add(demande);
	}

	@Listen("onClickActionButton = window")
	public void onClickActionButton(Event event) {

		String action = (String) event.getData();
		// Ajouter
		if (action.equalsIgnoreCase("CREER_DEMANDE")) {
			Window window = (Window) Executions.createComponents(
					"/pages/demande/creer.zul", getSelf(), null);
			window.setMode(Mode.MODAL);
			// Supprimer
		} else if (action.equalsIgnoreCase("SUPPRIMER_DEMANDE")) {
			if (listDemande.getSelectedItem() != null) {
				confirm("Etes vous sur de bien vouloir supprimer l'element?",
						"Suppression TDR",
						new EventListener<Messagebox.ClickEvent>() {

							@Override
							public void onEvent(ClickEvent event)
									throws Exception {

								if (event.getButton() == Button.YES) {
									Events.postEvent("onDelete",
											(Window) getSelf().getSpaceOwner(),
											null);
								}
							}
						});

			} else {
				alert("Veuillez Selectionner un element d'abord");
			}
		} else if (action.equalsIgnoreCase("MODIFIER_DEMANDE")) {
			if (listDemande.getSelectedItem() != null) {
				DemandeTDR a = new DemandeTDR();
				a = listDemande.getSelectedItem().getValue();
				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("demande", a);
				Window window = (Window) Executions.createComponents(
						"/pages/demande/modifier.zul", getSelf(), params);
				window.setMode(Mode.MODAL);

			} else
				alert("Veuillez Selectionner un element d'abord");
		} else if (action.equalsIgnoreCase("VOIR_ORDRE")) {
			if (listDemande.getSelectedItem() != null) {
				DemandeTDR a = new DemandeTDR();
				a = listDemande.getSelectedItem().getValue();
				if (a.getIsControleurAllowed()) {
					HashMap<String, Object> params = new HashMap<String, Object>();
					params.put("demande", a);
					Window window = (Window) Executions.createComponents(
							"/pages/demande/ordre_decaissement.zul", getSelf(),
							params);
					window.setMode(Mode.MODAL);
				} else
					alert("L'ordre de décaissement n'est pas encore validé  ");
			} else
				alert("Veuillez Selectionner un element d'abord");

		} else if (action.equalsIgnoreCase("VOIR_DEMANDE")) {
			if (listDemande.getSelectedItem() != null) {
				DemandeTDR a = new DemandeTDR();
				a = listDemande.getSelectedItem().getValue();

				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("demande", a);
				if (uf.getFonction().getLibelle()
						.equalsIgnoreCase("Superviseur")) {
					Window window = (Window) Executions.createComponents(
							"/pages/demande/voir_superviseur.zul", getSelf(),
							params);
					window.setMode(Mode.MODAL);
				} else if (uf.getFonction().getLibelle()
						.equalsIgnoreCase("DRH")) {
					Window window = (Window) Executions.createComponents(
							"/pages/demande/ordreMission.zul", getSelf(),
							params);
					window.setMode(Mode.MODAL);
				} else if (uf.getFonction().getLibelle()
						.equalsIgnoreCase("Patrimoine")) {
					Window window = (Window) Executions.createComponents(
							"/pages/demande/voir_ressource.zul", getSelf(),
							params);
					window.setMode(Mode.MODAL);
				} else if (uf.getFonction().getLibelle()
						.equalsIgnoreCase("Controleur")) {
					Window window = (Window) Executions.createComponents(
							"/pages/demande/voir_controleur.zul", getSelf(),
							params);
					window.setMode(Mode.MODAL);
				} else if (uf.getFonction().getLibelle()
						.equalsIgnoreCase("DGA")) {
					Window window = (Window) Executions.createComponents(
							"/pages/demande/ordreMissionDGA.zul", getSelf(),
							params);
					window.setMode(Mode.MODAL);
				} else if (uf.getFonction().getLibelle()
						.equalsIgnoreCase("Comptable")) {
					Window window = (Window) Executions.createComponents(
							"/pages/demande/ordreMissionCompta.zul", getSelf(),
							params);
					window.setMode(Mode.MODAL);
				} else {
					if (a.getIsComptaAllowed()) {
						Window window = (Window) Executions.createComponents(
								"/pages/demande/ordreMissionUser.zul",
								getSelf(), params);
						window.setMode(Mode.MODAL);
					} else {
						alert("L'ordre de mission n'est pas encore validé ");
					}
				}

			} else {
				alert("Veuillez Selectionner un element d'abord");
			}

		}
	}

	@Listen("onDelete=window")
	public void supprimerMinistere() throws Exception {
		DemandeTDRFacade DemandeTDRService = (DemandeTDRFacade) JNDIUtils
				.lookUpEJB(EJBRegistry.DemandeTDRFacade);
		DemandeTDR DemandeTDR = listDemande.getSelectedItem().getValue();
		if (DemandeTDRService.supprimer(DemandeTDR) == null) {
			alert("Suppression impossible");
			return;
		}
		((ListModelList<DemandeTDR>) demandeModel).remove(listDemande
				.getSelectedIndex());
	}

	public void confirm(String message, String titre,
			EventListener<Messagebox.ClickEvent> eventListener) {
		Messagebox.show(message, titre, new Messagebox.Button[] {
				Messagebox.Button.YES, Messagebox.Button.NO },
				Messagebox.QUESTION, eventListener);
	}

}
