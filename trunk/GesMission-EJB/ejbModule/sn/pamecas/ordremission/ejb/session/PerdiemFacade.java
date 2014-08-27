package sn.pamecas.ordremission.ejb.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.pamecas.ordremission.ejb.entites.Perdiem;

@Stateless
public class PerdiemFacade extends AbstractFacade<Perdiem> {
	@PersistenceContext
	private EntityManager em;

	public PerdiemFacade() {
		super(Perdiem.class);

	}

	@Override
	protected EntityManager getEntityManager() {

		return em;
	}

}
