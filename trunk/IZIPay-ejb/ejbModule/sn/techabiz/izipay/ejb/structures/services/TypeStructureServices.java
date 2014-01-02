package sn.techabiz.izipay.ejb.structures.services;

import java.util.List;

import javax.ejb.Remote;

import sn.techabiz.izipay.ejb.structures.entities.TypeStructure;

@Remote
public interface TypeStructureServices {
	
	public void create(TypeStructure ts);

	public List<TypeStructure> findAll();
}
