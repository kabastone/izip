package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ValeurRessource
 * 
 */
@Entity
@Table(name = "valeur_ressources")
public class ValeurRessource implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VR_ID")
	private Long id;
	@Column(name = "VR_CODE")
	private String code;
	@Column(name = "VR_VALEUR")
	private Float valeur;
	@Column(name = "VR_IS_ALLOWED")
	private Boolean isAllowed;
	private static final long serialVersionUID = 1L;

	public ValeurRessource() {
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

	public Float getValeur() {
		return this.valeur;
	}

	public void setValeur(Float valeur) {
		this.valeur = valeur;
	}

	public Boolean getIsAllowed() {
		return isAllowed;
	}

	public void setIsAllowed(Boolean isllowed) {
		this.isAllowed = isllowed;
	}

	

}
