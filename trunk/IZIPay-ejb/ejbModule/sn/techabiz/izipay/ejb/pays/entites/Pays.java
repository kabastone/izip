package sn.techabiz.izipay.ejb.pays.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pays
 *
 */
@Entity
@Table(name="pays")

public class Pays implements Serializable {

	   
	@Id
	private Long id;
	private String libelle;
	private String devise;
	private static final long serialVersionUID = 1L;

	public Pays() {
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
	public String getDevise() {
		return this.devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}
   
}
