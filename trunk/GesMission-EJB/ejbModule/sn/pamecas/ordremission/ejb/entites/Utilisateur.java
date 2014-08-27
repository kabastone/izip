package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Utilisateur
 * 
 */
@Entity
@Table(name = "utilisateurs")
public class Utilisateur implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USR_ID")
	private Long id;
	@Column(name = "USR_NOM")
	private String nom;
	@Column(name = "USR_PRENOM")
	private String prenom;
	@Column(name = "USR_LOGIN")
	private String login;
	@Column(name = "USR_PWD")
	private String password;
	@Column(name = "USR_EMAIL")
	private String email;
	@ManyToOne
	@JoinColumn(name = "ENT_ID_PARENT")
	private Entite entite;
	@ManyToOne
	@JoinColumn(name = "ST_ID_PARENT")
	private Statut statut;
	@ManyToOne
	@JoinColumn(name = "FCT_ID_PARENT")
	private Fonction fonction;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Groupe> groupes;
	private static final long serialVersionUID = 1L;

	public Utilisateur() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Entite getEntite() {
		return entite;
	}

	public void setEntite(Entite entite) {
		this.entite = entite;
	}

	public Fonction getFonction() {
		return fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	public Set<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(Set<Groupe> groupes) {
		this.groupes = groupes;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
