package sn.techabiz.izipay.web.vm;

import java.util.Set;
import java.util.TreeSet;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.BookmarkEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

import sn.techabiz.izipay.ejb.utilisateurs.entites.Action;
import sn.techabiz.izipay.ejb.utilisateurs.entites.Profil;
import sn.techabiz.izipay.ejb.utilisateurs.services.ActionServices;
import sn.techabiz.izipay.ejb.utilisateurs.services.ProfilServices;
import sn.techabiz.izipay.services.JNDIOutils;
import sn.techabiz.izipay.services.RegistreEJB;

public class MetierVM {
	@Wire("tree")
	Tree tree;

	@Wire("tabbox")
	Tabbox tabbox;

	Set<String> groupes = new TreeSet<String>();
	Include mainInclude = new Include();

	private ProfilServices profilServices = (ProfilServices) JNDIOutils
			.chercheEJB(RegistreEJB.ProfilFacade);

	private ActionServices actionServices = (ActionServices) JNDIOutils
			.chercheEJB(RegistreEJB.ActionFacade);

	private Profil profil = profilServices.find(1l);

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	@AfterCompose
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		initMenu();
	}

	public void initSidebar(String groupe) {
		tree.clear();
		Treechildren treechildren = tree.getTreechildren();

		tree.appendChild(treechildren);

		Treeitem treeitem;
		for (Action a : profil.getActions()) {
			if (a.getGroupe().equals(groupe)) {
				treeitem = new Treeitem(a.getNom());
				treeitem.setValue(a.getId());
				treeitem.addEventListener(Events.ON_CLICK, changeUrl);
				treechildren.appendChild(treeitem);
			}
		}
	}

	public void initMenu() {

		for (Action a : profil.getActions()) {
			groupes.add(a.getGroupe());
		}
		Tabpanels tabpanels = new Tabpanels();
		Tabs tabs = new Tabs();

		Tab tab;
		Tabpanel tabpanel;
		Include include;
		int i = 0;
		for (String s : groupes) {
			if (i == 0) {
				initSidebar(s);
				i = 1;
			}
			tab = new Tab(s);
			tab.addEventListener(Events.ON_CLICK, chargerSidebar);
			tab.setId(s + "tab");

			tabpanel = new Tabpanel();
			include = new Include();

			for (Action a : profil.getActions()) {
				if (a.getGroupe().equals(s)) {
					include.setSrc(a.getUrl());
					include.setId(s + "url");
					break;
				}
			}

			tabpanel.setHeight("500px");
			tabpanel.setAttribute("fulfill", tab.getId() + ".onClick");
			tabpanel.appendChild(include);
			tabpanels.appendChild(tabpanel);

			tabs.appendChild(tab);
		}

		tabbox.appendChild(tabpanels);
		tabbox.appendChild(tabs);
	}

	EventListener<Event> changeUrl = new EventListener<Event>() {

		@Override
		public void onEvent(Event event) throws Exception {
			Treeitem treeitem = (Treeitem) event.getTarget();
			Long id = (Long) treeitem.getValue();

			Action a = actionServices.find(id);

			Include include = (Include) treeitem.getFellow(a.getGroupe()
					+ "url");
			include.setSrc(a.getUrl());
			include.getPage().getDesktop()
					.setBookmark("p_" + a.getGroupe() + "_" + a.getNom());
		}
	};

	EventListener<Event> chargerSidebar = new EventListener<Event>() {

		@Override
		public void onEvent(Event event) throws Exception {
			Tab tab = (Tab) event.getTarget();
			String groupe = tab.getId().replace("tab", "");
			initSidebar(groupe);
		}
	};

	EventListener<BookmarkEvent> chargePage = new EventListener<BookmarkEvent>() {
		
		@Override
		public void onEvent(BookmarkEvent event) throws Exception {
			String bookmark = event.getBookmark();
		}
	};

}
