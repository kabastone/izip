package sn.pamecas.ordremission.ejb.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.pamecas.ordremission.ejb.entites.DemandeTDR;
import sn.pamecas.ordremission.ejb.entites.ValeurRessource;

@Stateless
public class ValeurRessourceFacade extends AbstractFacade<ValeurRessource> {
	@PersistenceContext
	private EntityManager em;

	public ValeurRessourceFacade() {
		super(ValeurRessource.class);

	}

	@Override
	protected EntityManager getEntityManager() {

		return em;
	}

	@SuppressWarnings("unchecked")
	public List<ValeurRessource> displayValResPatrimoine(DemandeTDR tdr) {
		Query query = em
				.createQuery(
						"From ValeurRessource vr where vr.ressource.typeRessource.code = 'Patrimoine' and vr.tdr = ? and vr.isAllowed = 0")
				.setParameter(1, tdr);
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<ValeurRessource> displayValResCompta(DemandeTDR demande) {
		Query query = em
				.createQuery(
						"From ValeurRessource vr where vr.ressource.typeRessource.entite.code = 'Comptabilite' and vr.tdr = ? and vr.isAllowed = 0")
				.setParameter(1, demande);
		return query.getResultList();
	}

	public List<ValeurRessource> displayValResDRH(DemandeTDR demande) {
		Query query = em
				.createQuery(
						"From ValeurRessource vr where vr.tdr = ? and vr.isAllowed = 1")
				.setParameter(1, demande);
		return query.getResultList();

	}

}
