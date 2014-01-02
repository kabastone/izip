package sn.techabiz.izipay.ejb.pays.services;

import java.util.List;

import javax.ejb.Remote;

import sn.techabiz.izipay.ejb.pays.entites.Pays;

@Remote
public interface PaysServices {
	public void create(Pays p);
	public List<Pays> findAll();
}
