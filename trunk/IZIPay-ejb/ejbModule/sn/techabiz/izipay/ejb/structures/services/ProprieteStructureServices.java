package sn.techabiz.izipay.ejb.structures.services;

import java.util.List;

import javax.ejb.Remote;

import sn.techabiz.izipay.ejb.structures.entities.ProprieteStructure;

@Remote
public interface ProprieteStructureServices {
	public List<ProprieteStructure> findAll();
}
