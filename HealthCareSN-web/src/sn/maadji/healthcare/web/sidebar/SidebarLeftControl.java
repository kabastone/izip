package sn.maadji.healthcare.web.sidebar;

import java.util.EventListener;
import java.util.HashSet;
import java.util.Set;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.zul.Nav;
import org.zkoss.zkmax.zul.Navbar;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.Include;
import org.zkoss.zul.impl.MessageboxDlg;

import sn.maadji.healthcare.ejb.entite.Acces;
import sn.maadji.healthcare.ejb.entite.Role;
import sn.maadji.healthcare.web.authentification.UserInfo;

public class SidebarLeftControl extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Navbar navbar;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub

		super.doAfterCompose(comp);
		// récupération de l'utilisateur connecté
		UserInfo<?> userInfo = (UserInfo<?>) Sessions.getCurrent()
				.getAttribute("userInfo");
		// récupération du role de l'utilisateur connecté
		if (!(userInfo == null)) {

			if (userInfo.getFonctions().size() == 1) {
				userInfo.setProfil(userInfo.getFonctions().iterator().next());
				Sessions.getCurrent().setAttribute("userInfo", userInfo);
			}
			if (userInfo.getProfil() != null) {
				org.zkoss.zul.Messagebox.show(userInfo.getProfil().getRoles()
						.size()
						+ "");

				buildSidebar(userInfo.getProfil().getRoles());
			}

		}
	}

	private void buildSidebar(final Set<Role> roles) {

		for (final Role r : roles) {
			Nav nav = new Nav();
			if (r.getListAcces().size() == 0) {
				Navitem item = new Navitem();
				item.setLabel(r.getName());
				org.zkoss.zk.ui.event.EventListener<Event> onEventListener = new SerializableEventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						// chargement de la page central
						Include include = (Include) Selectors
								.iterable(navbar.getPage(), "#mainInclude")
								.iterator().next();
						include.setSrc(r.getUrlRole());
						if (r.getName() != null) {
							getPage().getDesktop().setBookmark(
									"p_" + r.getName());
						}

					}
				};
				item.addEventListener(Events.ON_CLICK, onEventListener);
				item.setParent(navbar);

			} else {
				for (final Acces a : r.getListAcces()) {

					Navitem item = new Navitem();

					item.setLabel(a.getLibelleAcces());
					org.zkoss.zk.ui.event.EventListener<Event> onEventListener = new SerializableEventListener<Event>() {

						@Override
						public void onEvent(Event event) throws Exception {
							// chargement de la page central
							Include include = (Include) Selectors
									.iterable(navbar.getPage(), "#mainInclude")
									.iterator().next();
							include.setSrc(a.getUrlAcces());
							if (a.getLibelleAcces() != null) {
								getPage().getDesktop().setBookmark(
										"p_" + a.getLibelleAcces());
							}

						}
					};
					item.addEventListener(Events.ON_CLICK, onEventListener);
					// item.setParent(navbar);
					item.setParent(nav);
					nav.setLabel(r.getName());

				}
				nav.setParent(navbar);
			}

		}

	}
}
