package sn.maadji.healthcare.web.authentification;

import java.io.Serializable;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import sn.maadji.healthcare.ejb.entite.Utilisateur;
import sn.maadji.healthcare.ejb.session.UtilisateurFacade;
import sn.maadji.healthcare.web.outils.EJBRegistry;
import sn.maadji.healthcare.web.outils.JNDIUtils;



// Referenced classes of package sn.pamecas.web.convertexcel.authentification:
//            AuthentificationService, UserInfo

public class AuthentificationServiceImpl
    implements AuthentificationService, Serializable
{

    private UtilisateurFacade uf;
    private static final long serialVersionUID = 0xfcfcd2746fc53af0L;

    public AuthentificationServiceImpl()
    {
        uf = (UtilisateurFacade)JNDIUtils.lookUpEJB(EJBRegistry.UtilisateurFacade);
    }

    public boolean login(String l, String pwd)
    {
        Utilisateur utilisateur = uf.findByLogin(l);
        if(utilisateur == null)
        {
            return false;
        }
        if(!utilisateur.getPassword().equals(pwd))
        {
            return false;
        } else
        {
            UserInfo userInfo = new UserInfo(utilisateur.getNumUtilisateur(), utilisateur.getIdentifiant(), utilisateur.getFonctions(), utilisateur.getMail());
            Session session = Sessions.getCurrent();
            session.setAttribute("userInfo", userInfo);
            return true;
        }
    }

    public void logout()
    {
        Session session = Sessions.getCurrent();
        session.removeAttribute("userInfo");
    }

    public UserInfo getUserInfo()
    {
        Session session = Sessions.getCurrent();
        UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
        if(userInfo == null)
        {
            userInfo = new UserInfo();
            session.setAttribute("userInfo`", userInfo);
        }
        return userInfo;
    }
}
