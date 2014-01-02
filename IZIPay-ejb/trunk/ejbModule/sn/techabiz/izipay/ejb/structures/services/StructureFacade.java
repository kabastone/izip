package sn.techabiz.izipay.ejb.structures.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.techabiz.izipay.ejb.structures.entities.Structure;
import sn.techabiz.izipay.services.AbstractFacade;

/**
 * Session Bean implementation class StructureFacade
 */
@Stateless
public class StructureFacade extends AbstractFacade<Structure> implements
		StructureServices {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public StructureFacade() {
		super(Structure.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<Structure> findByParent(Structure s) {
		try {
			Query q = em.createNamedQuery("Structure.findByParent")
					.setParameter("parent", s);
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
