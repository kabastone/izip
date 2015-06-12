package sn.maadji.healthcare.web.authentification;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

// Referenced classes of package sn.pamecas.web.convertexcel.authentification:
//            AuthentificationServiceImpl, AuthentificationService

public class LogoutControl extends SelectorComposer<Component>
{

    private static final long serialVersionUID = 1L;
    AuthentificationService authService;
    
   
    public LogoutControl()
    {
        authService = new AuthentificationServiceImpl();
    }
    @Listen("onClick=#logout")
    public void doLogout()
    {
        authService.logout();
        Executions.sendRedirect("/authentification.zul");
    }
}
