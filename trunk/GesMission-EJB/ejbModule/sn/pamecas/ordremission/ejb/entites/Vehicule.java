package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Vehicule
 *
 */
@Entity

public class Vehicule implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="VL_ID")
	private Long id;
	@Column(name="VL_MATRICULE")
	private String matricule;
	@Column(name="VL_CHAUFFEUR")
	private String Chauffeur;
	@ManyToOne
	@JoinColumn(name="DMD_ID_PARENT")
	private DemandeTDR demande;
	private static final long serialVersionUID = 1L;

	public Vehicule() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}   
	public String getChauffeur() {
		return this.Chauffeur;
	}

	public void setChauffeur(String Chauffeur) {
		this.Chauffeur = Chauffeur;
	}
	public DemandeTDR getDemande() {
		return demande;
	}
	public void setDemande(DemandeTDR demande) {
		this.demande = demande;
	}
   
}
