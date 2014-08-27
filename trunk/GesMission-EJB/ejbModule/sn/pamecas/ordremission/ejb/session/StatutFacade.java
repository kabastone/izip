package sn.pamecas.ordremission.ejb.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.pamecas.ordremission.ejb.entites.Statut;

@Stateless
public class StatutFacade extends AbstractFacade<Statut> {
    @PersistenceContext
	private EntityManager em;

	public StatutFacade() {
		super(Statut.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
