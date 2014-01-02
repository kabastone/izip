package sn.techabiz.izipay.ejb.utilisateurs.services;

import javax.ejb.Remote;

import sn.techabiz.izipay.ejb.utilisateurs.entites.Utilisateur;

@Remote
public interface UtilisateurServices {
	public void create(Utilisateur u);

	public Utilisateur findByLogin(String l);
}
