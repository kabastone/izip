package sn.techabiz.izipay.ejb.structures.services;

import java.util.List;

import javax.ejb.Remote;

import sn.techabiz.izipay.ejb.structures.entities.ParametreComptable;

@Remote
public interface ParametreComptableServices {
	public void create(ParametreComptable p);
	public List<ParametreComptable> findAll();
	public void edit(ParametreComptable p);

}
