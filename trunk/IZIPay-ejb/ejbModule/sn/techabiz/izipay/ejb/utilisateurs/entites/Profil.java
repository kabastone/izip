package sn.techabiz.izipay.ejb.utilisateurs.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Profil
 *
 */
@Entity
@Table(name="profils")

public class Profil implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Action> actions;
	private static final long serialVersionUID = 1L;

	public Profil() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public Set<Action> getActions() {
		return actions;
	}
	public void setActions(Set<Action> actions) {
		this.actions = actions;
	}
   
}
