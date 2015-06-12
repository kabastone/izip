package sn.maadji.healthcare.web.authentification;


// Referenced classes of package sn.pamecas.web.convertexcel.authentification:
//            UserInfo

public interface AuthentificationService
{

    public abstract boolean login(String s, String s1);

    public abstract void logout();

    public abstract UserInfo getUserInfo();
}
