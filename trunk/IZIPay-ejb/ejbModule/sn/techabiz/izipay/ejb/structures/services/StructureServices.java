package sn.techabiz.izipay.ejb.structures.services;

import java.util.List;

import javax.ejb.Remote;

import sn.techabiz.izipay.ejb.structures.entities.Structure;

@Remote
public interface StructureServices {

	public void create(Structure s);

	public void edit(Structure s);

	public List<Structure> findAll();

	public List<Structure> findByParent(Structure s);

	public Structure find(Object id);
}
