package sn.pamecas.ordremission.ejb.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.pamecas.ordremission.ejb.entites.DemandeTDR;
import sn.pamecas.ordremission.ejb.entites.Vehicule;

@Stateless
public class VehiculeFacade extends AbstractFacade<Vehicule> {

	@PersistenceContext
	private EntityManager em;

	public VehiculeFacade() {
		super(Vehicule.class);

	}

	@Override
	protected EntityManager getEntityManager() {

		return em;
	}

	public List<Vehicule> findByDemande(DemandeTDR d) {
		Query query = em.createQuery("FROM Vehicule v where v.demande = ?")
				.setParameter(1, d);
		return query.getResultList();

	}
}
