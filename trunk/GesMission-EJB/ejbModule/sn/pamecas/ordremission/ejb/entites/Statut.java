package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Statut
 * 
 */
@Entity
@Table(name = "statuts")
public class Statut implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ST_ID")
	private Long id;
	@Column(name = "ST_CODE")
	private String code;
	@ManyToOne
	@JoinColumn(name = "PR_ID_PARENT")
	Perdiem perdiem;
	private static final long serialVersionUID = 1L;

	public Statut() {
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

	public Perdiem getPerdiem() {
		return perdiem;
	}

	public void setPerdiem(Perdiem perdiem) {
		this.perdiem = perdiem;
	}

}
