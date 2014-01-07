package sn.techabiz.izipay.ejb.structures.entities;

import java.io.Serializable;
import java.lang.Long;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ValeurProprieteStructure
 * 
 */
@Entity
@Table(name = "VALEURS_PROPRIETES_STRUCTURES")
public class ValeurProprieteStructure implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Structure structure;

	@ManyToOne
	private ProprieteStructure proprieteStructure;
	
	private String valeur;
	
	private static final long serialVersionUID = 1L;

	public ValeurProprieteStructure() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	public ProprieteStructure getProprieteStructure() {
		return proprieteStructure;
	}

	public void setProprieteStructure(ProprieteStructure proprieteStructure) {
		this.proprieteStructure = proprieteStructure;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

}
