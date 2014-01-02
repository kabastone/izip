package sn.techabiz.izipay.ejb.utilisateurs.entites;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

import sn.techabiz.izipay.ejb.pays.entites.Piece;

/**
 * Entity implementation class for Entity: Personne
 *
 */
@Entity
@Table(name="personnes")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TYPE_PERSONNE")
@DiscriminatorValue("PERSONNE")
public class Personne implements Serializable {

	   
	@Id
	private Long id;
	private String nom;
	private String prenom;
	private Integer Cellulaire;
	@OneToOne
	private Piece piece;
	private static final long serialVersionUID = 1L;

	public Personne() {
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
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}   
	public Integer getCellulaire() {
		return this.Cellulaire;
	}

	public void setCellulaire(Integer Cellulaire) {
		this.Cellulaire = Cellulaire;
	}
   
}
