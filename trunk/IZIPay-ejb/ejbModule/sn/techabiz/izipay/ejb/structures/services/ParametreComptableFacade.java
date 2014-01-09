package sn.techabiz.izipay.ejb.structures.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.techabiz.izipay.ejb.structures.entities.ParametreComptable;
import sn.techabiz.izipay.services.AbstractFacade;

/**
 * Session Bean implementation class ParametreComptableFacade
 */
@Stateless
public class ParametreComptableFacade extends AbstractFacade<ParametreComptable> implements ParametreComptableServices {

    @PersistenceContext
    EntityManager em;
    public ParametreComptableFacade() {
        super(ParametreComptable.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

}
