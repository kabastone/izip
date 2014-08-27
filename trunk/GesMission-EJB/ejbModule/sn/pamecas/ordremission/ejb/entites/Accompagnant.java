package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Accompagnant
 * 
 */
@Entity
@Table(name = "accompagnants")
public class Accompagnant implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AC_ID")
	private Long id;
	@Column(name = "AC_NOM")
	private String nom;
	@Column(name = "AC_PRENOM")
	private String prenom;
	@Column(name = "AC_FONCTION")
	private String fonction;
	@Column(name = "AC_ENTITE")
	private String entite;
	@ManyToOne
	@JoinColumn(name = "ST_ID_PARENT")
	private Statut statut;
	private static final long serialVersionUID = 1L;

	public Accompagnant() {
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

	public String getFonction() {
		return this.fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getEntite() {
		return this.entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

}
