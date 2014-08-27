package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: OrdreMission
 *
 */
@Entity
@Table(name="ordremissions")
public class OrdreMission implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OM_ID")
	private Long id;
	@Column(name="OM_LIEU")
	private String lieu;
	@Column(name="OM_OBJET")
	private String objet;
	private static final long serialVersionUID = 1L;

	public OrdreMission() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getLieu() {
		return this.lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}   
	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}
   
}
