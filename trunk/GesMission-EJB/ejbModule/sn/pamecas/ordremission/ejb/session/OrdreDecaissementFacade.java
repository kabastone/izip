package sn.pamecas.ordremission.ejb.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.pamecas.ordremission.ejb.entites.OrdreDecaissement;

@Stateless
public class OrdreDecaissementFacade extends AbstractFacade<OrdreDecaissement>{
    @PersistenceContext
	private EntityManager em;

	public OrdreDecaissementFacade() {
		super(OrdreDecaissement.class);
		
	}

	@Override
	protected EntityManager getEntityManager() {
		
		return em;
	}

}
