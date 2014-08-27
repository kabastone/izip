package sn.pamecas.ordremission.ejb.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.pamecas.ordremission.ejb.entites.Utilisateur;

@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur>{
@PersistenceContext
private EntityManager em;
	public UtilisateurFacade() {
		super(Utilisateur.class);
		
	}

	@Override
	protected EntityManager getEntityManager() {
		
		return em;
	}

	public Utilisateur findByLogin(String l) {
		Query query = null;
		Utilisateur u = new Utilisateur();
		try {
			query = em.createQuery("FROM Utilisateur u WHERE u.login =?")
					.setParameter(1, l);
			u = (Utilisateur) query.getSingleResult();
		} catch (NoResultException e) {
			
			return null;
		}

		return u;
	}

}
