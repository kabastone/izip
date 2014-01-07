package sn.techabiz.izipay.ejb.structures.entities;

import java.io.Serializable;
import java.lang.Long;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProprieteStructure
 *
 */
@Entity
@Table(name="PROPRIETES_STRUCTURES")

public class ProprieteStructure implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code,description;
	private static final long serialVersionUID = 1L;

	public ProprieteStructure() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
   
}
