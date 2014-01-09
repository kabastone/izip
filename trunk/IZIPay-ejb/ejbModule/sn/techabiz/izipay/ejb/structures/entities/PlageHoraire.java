package sn.techabiz.izipay.ejb.structures.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: PlageHoraire
 * 
 */
@Entity
@Table(name = "PLAGES_HORAIRE")
@NamedQueries(value = { @NamedQuery(name = "PlageHosraire.findByAgence", query = "SELECT ph FROM PlageHoraire ph WHERE ph.agence = :agence") })
public class PlageHoraire implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Structure agence;

	private Integer debut, fin;

	private String jour;

	public Long getId() {
		return id;
	}

	public Structure getAgence() {
		return agence;
	}

	public String getJour() {
		return jour;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAgence(Structure agence) {
		this.agence = agence;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}

	public Integer getDebut() {
		return debut;
	}

	public Integer getFin() {
		return fin;
	}

	public void setDebut(Integer debut) {
		this.debut = debut;
	}

	public void setFin(Integer fin) {
		this.fin = fin;
	}

}
