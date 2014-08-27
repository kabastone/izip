package sn.pamecas.ordremission.ejb.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.pamecas.ordremission.ejb.entites.Depense;
import sn.pamecas.ordremission.ejb.entites.OrdreDecaissement;

@Stateless
public class DepenseFacade extends AbstractFacade<Depense> {
	@PersistenceContext
	private EntityManager em;

	public DepenseFacade() {
		super(Depense.class);

	}

	@Override
	protected EntityManager getEntityManager() {

		return em;
	}

	public List<Depense> findByOrdre(OrdreDecaissement ordre) {
		Query query = null;
		query = em.createQuery("From Depense d where d.ordre = ?")
				.setParameter(1, ordre);
		return query.getResultList();
		
	}
}
