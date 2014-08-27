package sn.pamecas.ordremission.ejb.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.pamecas.ordremission.ejb.entites.Entite;

@Stateless
public class EntiteFacade extends AbstractFacade<Entite> {
	@PersistenceContext
	EntityManager em;

	public EntiteFacade() {
		super(Entite.class);

	}

	@Override
	protected EntityManager getEntityManager() {

		return em;
	}

}
