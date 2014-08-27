package sn.pamecas.ordremission.ejb.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.pamecas.ordremission.ejb.entites.Fonction;

@Stateless
public class FonctionFacade extends AbstractFacade<Fonction> {
	@PersistenceContext
	private EntityManager em;

	public FonctionFacade() {
		super(Fonction.class);

	}

	@Override
	protected EntityManager getEntityManager() {

		return em;
	}

}
