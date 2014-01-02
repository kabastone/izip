package sn.techabiz.izipay.ejb.utilisateurs.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Groupe
 * 
 */
@Entity
@Table(name = "groupes")
public class Groupe implements Serializable, Comparable<Groupe> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Droit> droits;
	private static final long serialVersionUID = 1L;

	public Groupe() {
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

	public Set<Droit> getDroits() {
		return droits;
	}

	public void setDroits(Set<Droit> droits) {
		this.droits = droits;
	}

	@Override
	public int compareTo(Groupe o) {
		return getNom().compareTo(o.getNom());
	}

}
