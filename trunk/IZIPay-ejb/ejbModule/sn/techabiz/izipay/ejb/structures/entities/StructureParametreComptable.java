package sn.techabiz.izipay.ejb.structures.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "STRUCTURE_PARAMETRE_COMPTABLES")
public class StructureParametreComptable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private ParametreComptable paramComptable;
	private Structure structure;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PARAM_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name = "STRUCT_PC_ID")
	public ParametreComptable getParamComptable() {
		return paramComptable;
	}
	public void setParamComptable(ParametreComptable paramComptable) {
		this.paramComptable = paramComptable;
	}
	@ManyToOne
	@JoinColumn(name = "STRUCT_ID")
	public Structure getStructure() {
		return structure;
	}
	public void setStructure(Structure structure) {
		this.structure = structure;
	}
	
	
	

}
