/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.techabiz.izipay.ejb.utilisateurs.services;

import java.util.List;
import javax.ejb.Remote;
import sn.techabiz.izipay.ejb.utilisateurs.entites.Groupe;

/**
 *
 * @author HP
 */
@Remote
public interface GroupeServices {
    public List<Groupe> findAll();
}
