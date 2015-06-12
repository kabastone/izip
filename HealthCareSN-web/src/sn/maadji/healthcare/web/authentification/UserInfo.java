package sn.maadji.healthcare.web.authentification;

import java.io.Serializable;
import java.util.Set;

import sn.maadji.healthcare.ejb.entite.Fonction;

public class UserInfo<T>
    implements Serializable
{

    private String login;
    private Set<Fonction> Fonctions;
    private Fonction profil;
    private String email;
    private boolean anonyme;
    private Long id;
    private static final long serialVersionUID = 0x44f518e86ffb5840L;

    public UserInfo(Long id, String login2, Set<Fonction> Fonction, String email)
    {
        login = login2;
        Fonctions = Fonction;
        this.id = id;
        this.email = email;
    }

    public UserInfo()
    {
        login = "anonyme";
        Fonctions = null;
        profil = null;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public Set<Fonction> getFonctions()
    {
        return Fonctions;
    }

    public void setFonctions(Set<Fonction> Fonctions)
    {
        this.Fonctions = Fonctions;
    }

    public Fonction getProfil()
    {
        return profil;
    }

    public void setProfil(Fonction profil)
    {
        this.profil = profil;
    }

    public boolean isAnonyme()
    {
        if(login.equals("anonyme"))
        {
            anonyme = true;
        } else
        {
            anonyme = false;
        }
        return anonyme;
    }

    public void setAnonyme(boolean anonyme)
    {
        this.anonyme = anonyme;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
