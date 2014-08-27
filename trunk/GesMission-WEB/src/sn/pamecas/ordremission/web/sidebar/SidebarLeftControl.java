package sn.pamecas.ordremission.web.sidebar;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabs;

import sn.pamecas.ordremission.ejb.entites.Droit;
import sn.pamecas.ordremission.web.ressources.authentification.UserInfo;

public class SidebarLeftControl extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Tabbox tabAcces;

	@Wire
	private Tabs tabCategorie;

	@Wire
	private Grid fnList;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		Session s = Sessions.getCurrent();
		UserInfo<?> uf = (UserInfo<?>) s.getAttribute("userInfo");
		if (!(uf == null)) {
			if (uf.getGroupes().size() == 1) {
				uf.setProfil(uf.getGroupes().iterator().next());
				s.setAttribute("userInfo", uf);
			}

			if (uf.getProfil() != null) {
				constructionMenu(uf.getProfil().getDroits());
			}
			/*
			 * SortedSet<String> categories = new TreeSet<String>(); Map<String,
			 * Set<Droit>> map = new HashMap<String, Set<Droit>>(); if
			 * (uf.getGroupes().size() == 1) {
			 * uf.setProfil(uf.getGroupes().iterator().next());
			 * s.setAttribute("userInfo", uf); } if (!(uf.getProfil() == null)){
			 * for (Droit d : uf.getProfil().getDroits()) {
			 * categories.add(d.getCategorie()); } for (String st : categories)
			 * { Set<Droit> droits = new HashSet<Droit>(); for (Droit d :
			 * uf.getProfil().getDroits()) { if
			 * (st.equalsIgnoreCase(d.getCategorie())) { droits.add(d); }
			 * 
			 * } map.put(st, droits); } Tabpanels panels = new Tabpanels();
			 * for(String st : categories){ Tab tab = new Tab();
			 * tab.setLabel(st); tabCategorie.appendChild(tab);
			 * 
			 * Tabpanel panel = new Tabpanel(); Div div = new Div();
			 * div.setClass("btn-group-vertical"); Set<A> liens =
			 * constructionMenu(map.get(st)); for(A a : liens){
			 * div.appendChild(a); } panel.appendChild(div);
			 * panels.appendChild(panel); } tabAcces.appendChild(panels);
			 */
		}
	}

	private void constructionMenu(Set<Droit> droits) {
		Set<Droit> links = new TreeSet<Droit>(new Comparator<Droit>() {

			@Override
			public int compare(Droit arg0, Droit arg1) {
				return arg0.getNom().compareTo(arg1.getNom());
			}
		});
		links = droits;
		Rows rows = fnList.getRows();
		for (final Droit d : links) {

			Row r = new Row();
			//Label label = new Label(d.getNom());
			Div div = new Div();
			div.setHflex("1");
			div.setVflex("1");
			div.setSclass("btn-group-vertical");
			A a = new A();
			a.setLabel(d.getNom());
			a.setWidth("200px");
			a.setClass("btn btn-default");

			EventListener<Event> onActionListener = new SerializableEventListener<Event>() {

				/**
			 * 
			 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onEvent(Event event) throws Exception {

					if (d.getUrl().startsWith("http")) {
						Executions.getCurrent().sendRedirect(d.getUrl());
					} else {
						Include include = (Include) Selectors
								.iterable(fnList.getPage(), "#mainInclude")
								.iterator().next();
						include.setSrc(d.getUrl());
					}

					if (d.getNom() != null) {
						getPage().getDesktop().setBookmark("p_" + d.getNom());
					}
				}
			};

			a.addEventListener(Events.ON_CLICK, onActionListener);
			a.setParent(div);
			div.setParent(r);
			rows.appendChild(r);
		}

	}
}
