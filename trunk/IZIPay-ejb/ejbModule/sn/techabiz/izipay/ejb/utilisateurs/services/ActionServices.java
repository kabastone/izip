package sn.techabiz.izipay.ejb.utilisateurs.services;

import javax.ejb.Remote;

import sn.techabiz.izipay.ejb.utilisateurs.entites.Action;

@Remote
public interface ActionServices {
	public Action find(Object id);
}
