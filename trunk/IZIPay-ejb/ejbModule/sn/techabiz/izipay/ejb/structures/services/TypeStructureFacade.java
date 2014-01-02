package sn.techabiz.izipay.ejb.structures.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.techabiz.izipay.ejb.structures.entities.TypeStructure;
import sn.techabiz.izipay.services.AbstractFacade;

/**
 * Session Bean implementation class TypeStructureFacade
 */
@Stateless
public class TypeStructureFacade extends AbstractFacade<TypeStructure>
		implements TypeStructureServices {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public TypeStructureFacade() {
		super(TypeStructure.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
