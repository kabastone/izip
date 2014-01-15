package sn.techabiz.izipay.ejb.utilisateurs.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.techabiz.izipay.ejb.utilisateurs.entites.Profil;
import sn.techabiz.izipay.services.AbstractFacade;

/**
 * Session Bean implementation class ProfilFacade
 */
@Stateless
public class ProfilFacade extends AbstractFacade<Profil> implements
		ProfilServices {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ProfilFacade() {
		super(Profil.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
