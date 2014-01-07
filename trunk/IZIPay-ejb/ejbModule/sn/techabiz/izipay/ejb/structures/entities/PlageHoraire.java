package sn.techabiz.izipay.ejb.structures.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: PlageHoraire
 *
 */
@Entity
@Table(name="PLAGES_HORAIRE")

public class PlageHoraire implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Structure agence;
	private static final long serialVersionUID = 1L;

	public PlageHoraire() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Structure getAgence() {
		return agence;
	}

	public void setAgence(Structure agence) {
		this.agence = agence;
	}
   
}
