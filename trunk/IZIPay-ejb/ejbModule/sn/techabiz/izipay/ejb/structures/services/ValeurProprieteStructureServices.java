package sn.techabiz.izipay.ejb.structures.services;

import java.util.List;

import javax.ejb.Remote;

import sn.techabiz.izipay.ejb.structures.entities.Structure;
import sn.techabiz.izipay.ejb.structures.entities.ValeurProprieteStructure;

@Remote
public interface ValeurProprieteStructureServices {
	public void create(ValeurProprieteStructure vps);

	public void edit(ValeurProprieteStructure vps);

	public List<ValeurProprieteStructure> getValeurProprieteStructures(
			Structure s);
}
