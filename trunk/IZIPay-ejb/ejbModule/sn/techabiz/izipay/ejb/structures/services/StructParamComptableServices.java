package sn.techabiz.izipay.ejb.structures.services;

import java.util.List;

import javax.ejb.Remote;

import sn.techabiz.izipay.ejb.structures.entities.Structure;
import sn.techabiz.izipay.ejb.structures.entities.StructureParametreComptable;
import sun.font.CreatedFontTracker;

@Remote
public interface StructParamComptableServices {
	public void create(StructureParametreComptable structureParametreComptable);
	public List<StructureParametreComptable> findAll();
	public List<StructureParametreComptable> findByStructure(Structure s);

	public StructureParametreComptable find(Object id);

	public void edit(StructureParametreComptable sp);	

}
