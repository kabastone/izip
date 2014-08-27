package sn.pamecas.ordremission.web.ressources.authentification;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

public class LoginControl extends SelectorComposer<Component>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8862782328648811953L;
	@Wire
	Textbox account;
	@Wire
	Textbox password;
	@Wire
	Label message;

	// services
	AuthentificationService authService = new AuthentificationServiceImpl();

	@Listen("onClick=#login; onOK=#loginWin")
	public void doLogin() {
		String nm = account.getValue();
		String pd = password.getValue();

		if (!authService.login(nm, pd)) {
			message.setValue("le compte ou mot de passe est incorrect.");
		} else {
			UserInfo<?> userInfo = authService.getUserInfo();
			message.setValue("Welcome, " + userInfo.getLogin());
			message.setSclass("");

			Executions.sendRedirect("/index.zul");
		}

	}
}
