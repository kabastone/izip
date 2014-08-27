package sn.pamecas.ordremission.ejb.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.pamecas.ordremission.ejb.entites.OrdreMission;

@Stateless
public class OdreMissionFacade extends AbstractFacade<OrdreMission> {
	@PersistenceContext
	private EntityManager em;

	public OdreMissionFacade() {
		super(OrdreMission.class);

	}

	@Override
	protected EntityManager getEntityManager() {
		
		return em;
	}

}
