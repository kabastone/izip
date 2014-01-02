/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.techabiz.izipay.ejb.utilisateurs.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sn.techabiz.izipay.ejb.utilisateurs.entites.Groupe;
import sn.techabiz.izipay.services.AbstractFacade;

/**
 *
 * @author HP
 */
@Stateless
public class GroupeFacade extends AbstractFacade<Groupe> implements GroupeServices{

    @PersistenceContext
    private EntityManager em;
    public GroupeFacade() {
        super(Groupe.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
