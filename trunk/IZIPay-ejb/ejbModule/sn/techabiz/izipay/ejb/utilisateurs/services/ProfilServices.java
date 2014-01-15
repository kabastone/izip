package sn.techabiz.izipay.ejb.utilisateurs.services;

import javax.ejb.Remote;

import sn.techabiz.izipay.ejb.utilisateurs.entites.Profil;

@Remote
public interface ProfilServices {
	public Profil find(Object id);
}
