package sn.techabiz.izipay.ejb.pays.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.techabiz.izipay.ejb.pays.entites.Pays;
import sn.techabiz.izipay.services.AbstractFacade;

/**
 * Session Bean implementation class PaysFacade
 */
@Stateless
public class PaysFacade extends AbstractFacade<Pays> implements PaysServices{

	
	@PersistenceContext
	private EntityManager em;
	/**
	 * Default constructor.
	 */
	public PaysFacade() {
		super(Pays.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
