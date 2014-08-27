package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Fonction
 *
 */
@Entity
@Table(name="fontions")
public class Fonction implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FCT_ID")
	private Long id;
	@Column(name="FCT_LIBELLE")
	private String libelle;
	@Column(name="FCT_REFERENCE")
	private String reference;
	private static final long serialVersionUID = 1L;

	public Fonction() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}   
	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
   
}
