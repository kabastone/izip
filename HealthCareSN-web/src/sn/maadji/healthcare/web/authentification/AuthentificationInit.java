package sn.maadji.healthcare.web.authentification;

import java.util.Map;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

// Referenced classes of package sn.pamecas.web.convertexcel.authentification:
//            AuthentificationServiceImpl, AuthentificationService, UserInfo

public class AuthentificationInit
    implements Initiator
{

    AuthentificationService authentificationService;

    public AuthentificationInit()
    {
        authentificationService = new AuthentificationServiceImpl();
    }

    public void doInit(Page page, Map args)
        throws Exception
    {
        UserInfo userInfo = authentificationService.getUserInfo();
        if(userInfo == null || userInfo.isAnonyme())
        {
            Executions.sendRedirect("authentification.zul");
        }
    }
}
