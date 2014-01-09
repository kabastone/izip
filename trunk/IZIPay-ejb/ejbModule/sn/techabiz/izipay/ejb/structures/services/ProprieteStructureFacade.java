package sn.techabiz.izipay.ejb.structures.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.techabiz.izipay.ejb.structures.entities.ProprieteStructure;
import sn.techabiz.izipay.services.AbstractFacade;

/**
 * Session Bean implementation class ProprieteStructureFacade
 */
@Stateless
public class ProprieteStructureFacade extends AbstractFacade<ProprieteStructure> implements
		ProprieteStructureServices {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ProprieteStructureFacade() {
		super(ProprieteStructure.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
