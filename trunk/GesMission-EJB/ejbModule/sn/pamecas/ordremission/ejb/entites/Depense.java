package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Depense
 * 
 */
@Entity
@Table(name = "depenses")
public class Depense implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "D_ID")
	private Long id;
	@Column(name = "D_LIBELLE")
	private String libelle;
	private static final long serialVersionUID = 1L;

	public Depense() {
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

}
