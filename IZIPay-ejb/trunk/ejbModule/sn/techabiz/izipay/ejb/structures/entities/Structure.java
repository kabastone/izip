package sn.techabiz.izipay.ejb.structures.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "STRUCTURES")
@SequenceGenerator(name = "SEQ_STRUCTURES", sequenceName = "SEQ_STRUCTURES", allocationSize = 50, initialValue = 1000)
@NamedQueries(value = { @NamedQuery(name = "Structure.findByParent", query = "SELECT st FROM Structure st WHERE st.parent = :parent") })
public class Structure implements Serializable {
	private static final long serialVersionUID = 4289662014407459362L;

	private Long id;
	@NotNull
	private String libelle;
	@Valid
	private Structure parent;

	private Boolean internal, virtual;
	@Valid
	private TypeStructure type;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STRUCTURES")
	@Column(name = "STRUCT_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "STRUCT_LIBELLE")
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@ManyToOne
	@JoinColumn(name = "STRUCT_PARENT_ID")
	public Structure getParent() {
		return parent;
	}

	public void setParent(Structure parent) {
		this.parent = parent;
	}

	@Column(name = "STRUCT_INTERNAL")
	public Boolean getInternal() {
		return internal;
	}

	public void setInternal(Boolean internal) {
		this.internal = internal;
	}

	@Column(name = "STRUCT_VIRTUAL")
	public Boolean getVirtual() {
		return virtual;
	}

	public void setVirtual(Boolean virtual) {
		this.virtual = virtual;
	}

	@ManyToOne
	@JoinColumn(name = "STRUCT_TS_CODE")
	public TypeStructure getType() {
		return type;
	}

	public void setType(TypeStructure type) {
		this.type = type;
	}

}
