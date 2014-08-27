package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TypeRessource
 *
 */
@Entity
@Table(name="type_ressources")
public class TypeRessource implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TP_ID")
	private Long id;
	@Column(name="TP_CODE")
	private String code;
	@Column(name="TP_DESCRIPTION")
	private String description;
	@ManyToOne
	@JoinColumn(name="ENT_ID_PARENT")
	Entite entite;
	@OneToMany(fetch=FetchType.EAGER)
	private Set<ValeurRessource> valeurRessources;
	@OneToMany(fetch=FetchType.EAGER)
	private Set<Vehicule> vehicules;
	private static final long serialVersionUID = 1L;

	public TypeRessource() {
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
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Entite getEntite() {
		return entite;
	}
	public void setEntite(Entite entite) {
		this.entite = entite;
	}
	public Set<ValeurRessource> getValeurRessources() {
		return valeurRessources;
	}
	public void setValeurRessources(Set<ValeurRessource> valeurRessources) {
		this.valeurRessources = valeurRessources;
	}
   
}
