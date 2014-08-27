package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Droit
 *
 */
@Entity
@Table(name="droits")

public class Droit implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DRT_ID")
	private Long id;
	
	@Column(name="DRT_CATEGORIE")
	private String categorie;
	
	@Column(name="DRT_NOM")
	private String nom;
	
	@Column(name="DRT_URL")
	private String url;
	private static final long serialVersionUID = 1L;

	public Droit() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}   
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
   
}
