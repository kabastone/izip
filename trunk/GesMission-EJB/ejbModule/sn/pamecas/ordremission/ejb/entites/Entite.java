package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Entite
 *
 */
@Entity
@Table(name="entites")
public class Entite implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ENT_ID")
	private Long id;
	@Column(name="ENT_LIBELLE")
	private String Libelle;
	@Column(name="ENT_REFERENCE")
	private String reference;
	private static final long serialVersionUID = 1L;

	public Entite() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getLibelle() {
		return this.Libelle;
	}

	public void setLibelle(String Libelle) {
		this.Libelle = Libelle;
	}   
	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
   
}
