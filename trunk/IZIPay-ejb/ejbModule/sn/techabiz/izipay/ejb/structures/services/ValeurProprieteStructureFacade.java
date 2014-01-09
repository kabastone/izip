package sn.techabiz.izipay.ejb.structures.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.techabiz.izipay.ejb.structures.entities.Structure;
import sn.techabiz.izipay.ejb.structures.entities.ValeurProprieteStructure;
import sn.techabiz.izipay.services.AbstractFacade;

/**
 * Session Bean implementation class ValeurProprieteStructureFacade
 */
@Stateless
public class ValeurProprieteStructureFacade extends
		AbstractFacade<ValeurProprieteStructure> implements
		ValeurProprieteStructureServices {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ValeurProprieteStructureFacade() {
		super(ValeurProprieteStructure.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<ValeurProprieteStructure> getValeurProprieteStructures(
			Structure s) {
		try {
			Query q = em
					.createNamedQuery("ValeurProprieteStructure.findByStructure");
			q.setParameter("structure", s);
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
