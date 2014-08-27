package sn.pamecas.ordremission.web.ressources.authentification;

import java.io.Serializable;
import java.util.Set;

import sn.pamecas.ordremission.ejb.entites.Entite;
import sn.pamecas.ordremission.ejb.entites.Fonction;
import sn.pamecas.ordremission.ejb.entites.Groupe;
import sn.pamecas.ordremission.ejb.entites.Statut;

public class UserInfo<T> implements Serializable {

	/**
	 * 
	 */
	private String login;
	private Set<Groupe> groupes;
	private Groupe profil;
	private Entite entite;
	private Fonction fonction;
	private Statut statut;
	private String email;

	private boolean anonyme;
	private Long id;

	private static final long serialVersionUID = 4968905150463432768L;

	public UserInfo(Long id, String login2, Set<Groupe> groupe, Entite entite,
			Fonction fonction, Statut statut ,String email) {
		this.login = login2;
		this.groupes = groupe;
		this.entite = entite;
		this.id = id;
		this.fonction = fonction;
		this.statut = statut;
		this.email = email;
	}

	public UserInfo() {
		this.login = "anonyme";
		this.groupes = null;
		this.profil = null;
		this.entite = null;
		this.fonction = null;
		this.statut = null;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Set<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(Set<Groupe> groupes) {
		this.groupes = groupes;
	}

	public Groupe getProfil() {
		return profil;
	}

	public void setProfil(Groupe profil) {
		this.profil = profil;
	}

	public boolean isAnonyme() {
		if (login.equals("anonyme")) {
			anonyme = true;
		} else {
			anonyme = false;
		}
		return anonyme;
	}

	public void setAnonyme(boolean anonyme) {
		this.anonyme = anonyme;
	}

	public Entite getEntite() {
		return entite;
	}

	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	public Fonction getFonction() {

		return fonction;
	}

	public Statut getStatut() {
		// TODO Auto-generated method stub
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
