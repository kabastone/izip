package sn.techabiz.izipay.ejb.structures.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.techabiz.izipay.ejb.structures.entities.Structure;
import sn.techabiz.izipay.ejb.structures.entities.StructureParametreComptable;
import sn.techabiz.izipay.services.AbstractFacade;

/**
 * Session Bean implementation class StructParamComptableFacade
 */
@Stateless
public class StructParamComptableFacade extends
		AbstractFacade<StructureParametreComptable> implements
		StructParamComptableServices {

	@PersistenceContext
	EntityManager em;

	public StructParamComptableFacade() {
        super(StructureParametreComptable.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		
		return em;
	}

	@Override
	public List<StructureParametreComptable> findByStructure(Structure s) {
		
		try {
			Query q = em.createNamedQuery("StructureParametreComptable.findByStructure")
					.setParameter("structure", s);
			return q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
