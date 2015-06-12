package sn.maadji.healthcare.web.authentification;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

// Referenced classes of package sn.pamecas.web.convertexcel.authentification:
//            AuthentificationServiceImpl, AuthentificationService, UserInfo

public class LoginControl extends SelectorComposer<Component>
{

    private static final long serialVersionUID = 0x7afeed9224b8c5b1L;
    @Wire
    Textbox account;
    @Wire
    Textbox password;
    @Wire
    Label message;
    AuthentificationService authService;

    public LoginControl()
    {
        authService = new AuthentificationServiceImpl();
    }
    @Listen("onClick=#login; onOK=#loginWin")
    public void doLogin()
    {
        String nm = account.getValue();
        String pd = password.getValue();
        if(!authService.login(nm, pd))
        {
            message.setValue("le compte ou mot de passe est incorrect.");
        } else
        {
            UserInfo<?> userInfo = authService.getUserInfo();
            message.setValue((new StringBuilder("Welcome, ")).append(userInfo.getLogin()).toString());
            message.setSclass("");
            Executions.sendRedirect("/index.zul");
        }
    }
}
