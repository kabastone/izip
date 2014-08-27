package sn.pamecas.ordremission.web.ressources.authentification;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

public class AuthentificationInit implements Initiator{

	AuthentificationService authentificationService = new AuthentificationServiceImpl();
	
	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		UserInfo<?> userInfo = authentificationService.getUserInfo();
		
		if(userInfo == null || userInfo.isAnonyme()){
			Executions.sendRedirect("authentification.zul");
		}
	}

}
