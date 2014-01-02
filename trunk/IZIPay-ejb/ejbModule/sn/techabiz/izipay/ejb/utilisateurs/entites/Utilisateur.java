package sn.techabiz.izipay.ejb.utilisateurs.entites;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Utilisateur
 * 
 */
@Entity
@Table(name = "utilisateurs")
@DiscriminatorValue("UTILISATEUR")
@NamedQueries(value = { @NamedQuery(name = "Utilisateur.findByLogin", query = "SELECT u FROM Utilisateur u "
		+ "WHERE u.login = :login") })
public class Utilisateur extends Personne {

	private String login, password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Groupe> groupes;
	
	private static final long serialVersionUID = 1L;

	public Utilisateur() {
		super();
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

	public Set<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(Set<Groupe> groupes) {
		this.groupes = groupes;
	}

}
