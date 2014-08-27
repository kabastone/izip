package sn.pamecas.ordremission.ejb.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import sn.pamecas.ordremission.ejb.entites.TypeRessource;
@Stateless
public class TypeRessourceFacade extends AbstractFacade<TypeRessource> {
	@PersistenceContext
	private EntityManager em;

	public TypeRessourceFacade() {
		super(TypeRessource.class);

	}

	@Override
	protected EntityManager getEntityManager() {

		return em;
	}

}
