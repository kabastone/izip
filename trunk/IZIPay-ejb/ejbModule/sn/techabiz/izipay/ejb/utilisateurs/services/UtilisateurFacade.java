package sn.techabiz.izipay.ejb.utilisateurs.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.techabiz.izipay.ejb.utilisateurs.entites.Utilisateur;
import sn.techabiz.izipay.services.AbstractFacade;

/**
 * Session Bean implementation class UtilisateurFacade
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> implements
		UtilisateurServices {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public UtilisateurFacade() {
		super(Utilisateur.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public Utilisateur findByLogin(String l) {
		try {
			return (Utilisateur) getEntityManager()
					.createNamedQuery("Utilisateur.findByLogin")
					.setParameter("login", l).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
