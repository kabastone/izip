package sn.techabiz.izipay.ejb.utilisateurs.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.techabiz.izipay.ejb.utilisateurs.entites.Action;
import sn.techabiz.izipay.services.AbstractFacade;

/**
 * Session Bean implementation class ActionFacade
 */
@Stateless
public class ActionFacade extends AbstractFacade<Action> implements
		ActionServices {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ActionFacade() {
		super(Action.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
