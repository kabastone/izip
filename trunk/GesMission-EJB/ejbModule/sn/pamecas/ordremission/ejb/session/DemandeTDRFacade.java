package sn.pamecas.ordremission.ejb.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sn.pamecas.ordremission.ejb.entites.DemandeTDR;

@Stateless
public class DemandeTDRFacade extends AbstractFacade<DemandeTDR> {
	@PersistenceContext
	EntityManager em;

	public DemandeTDRFacade() {
		super(DemandeTDR.class);

	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<DemandeTDR> displayDemande() {
		Query query = em.createQuery("From DemandeTDR d where d.isDeleted=0");
		return query.getResultList();

	}

	public List<DemandeTDR> displayTDRPatrimoine() {
		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery("FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isAllowed = 0 and d.isPatAllowed = 0 and d.isSuperviseurAllowed = 1");
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;

	}

	public List<DemandeTDR> displayTDRPatrimoineValide() {
		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery("FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isPatAllowed = 1");
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;

	}

	public List<DemandeTDR> displayTDRSuperviseur() {
		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery("FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isAllowed = 0 and d.isSuperviseurAllowed = 0");
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;

	}

	public List<DemandeTDR> displayTDRSuperviseurValide() {
		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery("FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isSuperviseurAllowed = 1");
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;

	}

	public List<DemandeTDR> displayTDRComptable() {
		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery("FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isAllowed = 0 and d.isPatAllowed = 1 and d.isControleurAllowed = 1 and d.isDGAllowed = 1 and d.isComptaAllowed = 0");
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;

	}

	public List<DemandeTDR> displayTDRComptableValide() {
		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery("FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isComptaAllowed = 1");
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;

	}

	public List<DemandeTDR> displayTDRControleur() {
		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery("FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isAllowed = 0 and d.isPatAllowed = 1 and d.isControleurAllowed = 0 and d.isDGAllowed = 1 ");
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;

	}

	public List<DemandeTDR> displayTDRControleurValide() {
		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery("FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isControleurAllowed = 1");
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;

	}

	public DemandeTDR supprimer(DemandeTDR demandeTDR) {
		demandeTDR.setIsDeleted(true);
		;
		return em.merge(demandeTDR);
	}

	public List<DemandeTDR> displaytdrDRH() {

		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery("FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isAllowed = false and d.isPatAllowed = true  and d.isDRHAllowed = 0");
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;

	}

	public List<DemandeTDR> displaytdrDRHValide() {

		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery("FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isDRHAllowed = 1");
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;

	}

	public List<DemandeTDR> displaytdrDGA() {
		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery("FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isAllowed = false and d.isDRHAllowed = true");
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;
	}
	
	public List<DemandeTDR> displaytdrDGAValide() {
		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery("FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isDGAllowed = 1");
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;
	}

	public List<DemandeTDR> displayTDR(Long id) {
		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery(
							"FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isAllowed = 0 and d.utilisateur.id=:id")
					.setParameter("id", id);
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;
	}
	
	public List<DemandeTDR> displayTDRValide(Long id) {
		List<DemandeTDR> liste = new ArrayList<DemandeTDR>();
		Query query = null;
		try {
			query = em
					.createQuery(
							"FROM DemandeTDR d WHERE d.isDeleted = 0 and d.isAllowed = 1 and d.utilisateur.id = ?")
					.setParameter(1, id);
			liste = query.getResultList();
		} catch (Exception e) {
			return null;
		}
		return liste;
	}
}
