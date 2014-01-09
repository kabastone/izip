package sn.techabiz.izipay.ejb.structures.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.techabiz.izipay.ejb.structures.entities.PlageHoraire;
import sn.techabiz.izipay.ejb.structures.entities.Structure;
import sn.techabiz.izipay.services.AbstractFacade;

/**
 * Session Bean implementation class PlageHoraireFacade
 */
@Stateless
public class PlageHoraireFacade extends AbstractFacade<PlageHoraire> implements
		PlageHoraireServices {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public PlageHoraireFacade() {
		super(PlageHoraire.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public List<PlageHoraire> getPlaceHoraire(Structure s){
		try {
			Query q = em.createNamedQuery("PlageHosraire.findByAgence");
			q.setParameter("agence", s);
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
