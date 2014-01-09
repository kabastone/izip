package sn.techabiz.izipay.ejb.structures.services;

import java.util.List;

import javax.ejb.Remote;

import sn.techabiz.izipay.ejb.structures.entities.PlageHoraire;
import sn.techabiz.izipay.ejb.structures.entities.Structure;

@Remote
public interface PlageHoraireServices {
	public void create(PlageHoraire ph);

	public void edit(PlageHoraire ph);

	public List<PlageHoraire> getPlaceHoraire(Structure s);
}
