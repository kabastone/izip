package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Perdiem
 *
 */
@Entity
@Table(name="perdiems")
public class Perdiem implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PR_ID")
	private Long id;
	@Column(name="PR_CODE")
	private String code;
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<ValeurRessource> valeurRessources;
	
	private static final long serialVersionUID = 1L;

	public Perdiem() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public Set<ValeurRessource> getValeurRessources() {
		return valeurRessources;
	}
	public void setValeurRessources(Set<ValeurRessource> valeurRessources) {
		this.valeurRessources = valeurRessources;
	}
   
}
