package sn.pamecas.ordremission.web.ressources.authentification;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class LogoutControl extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8285565707906125721L;
	AuthentificationService authService = new AuthentificationServiceImpl();

	@Listen("onClick=#logout")
	public void doLogout() {
		authService.logout();
		Executions.sendRedirect("/authentification.zul");
	}
}
