package sn.pamecas.ordremission.web.sidebar;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;

import sn.pamecas.ordremission.ejb.entites.Groupe;
import sn.pamecas.ordremission.web.ressources.authentification.UserInfo;



public class SidebarRightControl extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Wire private Div block;
	       

	@Override
	public void doAfterCompose(Component comp) throws Exception {
	
		super.doAfterCompose(comp);
		//récupération des infos de l'utilisateur
		
		Session s = Sessions.getCurrent();
		UserInfo<?> uf = (UserInfo<?>) s.getAttribute("userInfo");
        //affichage des groupes de l'utilisateur dans un menu
		if (!(uf == null)) {
			if (!(uf.getGroupes() == null)) {
				if (uf.getGroupes().size() == 1) {
					uf.setProfil(uf.getGroupes().iterator().next());
					s.setAttribute("userInfo", uf);
				} else {
					
					for (Groupe g : uf.getGroupes()) {
					block.appendChild(contructionProfil(g))	;
						
					}
				}
			}

		}
	}
	private org.zkoss.zul.A contructionProfil(final Groupe g) {
		final org.zkoss.zul.A a = new org.zkoss.zul.A();
		a.setLabel(g.getNom());
		a.setWidth("200px");
		a.setClass("btn btn-default");
		a.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event event) throws Exception {
				Session s = Sessions.getCurrent();
				UserInfo<?> uf = (UserInfo<?>) s.getAttribute("userInfo");
				uf.setProfil(g);
				s.setAttribute("userInfo", uf);
				Executions.sendRedirect("index.zul");
			}
		});
		return a;
	}

}
