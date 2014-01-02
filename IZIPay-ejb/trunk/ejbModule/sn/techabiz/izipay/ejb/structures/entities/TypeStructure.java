package sn.techabiz.izipay.ejb.structures.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TYPES_STRUCTURES")
public class TypeStructure implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3139416905746826558L;

	@NotNull
	private String code;
	
	private String 	description;
	
	private Boolean autonome, virtualAllowed;
	@Valid
	private TypeStructure parent;

	@Id
	@Column(name = "TS_CODE",length = 15)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "TS_DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "TS_AUTONOME_ALLOWED")
	public Boolean getAutonome() {
		return autonome;
	}

	public void setAutonome(Boolean autonome) {
		this.autonome = autonome;
	}

	@Column(name = "TS_VIRTUAL_ALLOWED")
	public Boolean getVirtualAllowed() {
		return virtualAllowed;
	}

	public void setVirtualAllowed(Boolean virtualAllowed) {
		this.virtualAllowed = virtualAllowed;
	}

	@ManyToOne
	@JoinColumn(name = "TS_PARENT_CODE")
	public TypeStructure getParent() {
		return parent;
	}

	public void setParent(TypeStructure parent) {
		this.parent = parent;
	}

}
