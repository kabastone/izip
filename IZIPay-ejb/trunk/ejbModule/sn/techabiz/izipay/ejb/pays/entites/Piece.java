package sn.techabiz.izipay.ejb.pays.entites;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Piece
 *
 */
@Entity
@Table(name="pieces")

public class Piece implements Serializable {

	   
	@Id
	private Long id;
	private String libelle;
	private Integer numero;
	private Date dateExpiration;
	private static final long serialVersionUID = 1L;

	public Piece() {
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
	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}   
	public Date getDateExpiration() {
		return this.dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
   
}
