package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: OrdreDecaissement
 *
 */
@Entity
@Table(name="ordredecaissements")

public class OrdreDecaissement implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OD_ID")
	private Long id;
	@Column(name="OD_SERVICE_A")
	private String serviceA;
	@Column(name="OD_SERVICE_D")
	private String serviceD;
	@Column(name="OD_DATE")
	private String date;
	@Column(name="OD_MONTANT")
	private Float montant;
	@Column(name="OD_DESCRIPTION")
	private String description;
	@Column(name="OD_BUDGET")
	private String budget;
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Depense> depenses;
	private static final long serialVersionUID = 1L;

	public OrdreDecaissement() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getServiceA() {
		return this.serviceA;
	}

	public void setServiceA(String serviceA) {
		this.serviceA = serviceA;
	}   
	public String getServiceD() {
		return this.serviceD;
	}

	public void setServiceD(String serviceD) {
		this.serviceD = serviceD;
	}   
	public String getDate() {
		return this.date;
	}

	public void setDate(String dateFormat) {
		this.date = dateFormat;
	}   
	public Float getMontant() {
		return this.montant;
	}

	public void setMontant(Float montant) {
		this.montant = montant;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public Set<Depense> getDepenses() {
		return depenses;
	}
	public void setDepenses(Set<Depense> depenses) {
		this.depenses = depenses;
	}
   
}
