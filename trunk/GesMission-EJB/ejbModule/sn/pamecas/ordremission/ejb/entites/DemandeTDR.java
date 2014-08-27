package sn.pamecas.ordremission.ejb.entites;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DemandeTDR
 * 
 */
@Entity
@Table(name = "demandes")
public class DemandeTDR implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "DMD_OBJET")
	private String objet;
	@Column(name = "DMD_LIEU")
	private String lieu;
	@Column(name = "DMD_DATE_DEB")
	private String dateDeb;
	@Column(name = "DMD_DATE_FIN")
	private String dateFin;
	@Column(name = "DMD_IS_DELETED")
	private Boolean isDeleted;
	@ManyToOne
	@JoinColumn(name = "USR_ID_PARENT")
	private Utilisateur utilisateur;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<ValeurRessource> valeurRessources;
	@Column(name = "DMD_IS_ALLOWED")
	private Boolean isAllowed;
	@Column(name = "DMD_SIGNATURE_DRH")
	private String signatureDRH;
	@Column(name = "DMD_IS_DGA_ALLOWED")
	private Boolean isDGAllowed;
	@Column(name = "DMD_SIGNATURE_DGA")
	private String signatureDGA;
	@Column(name = "DMD_NBR_PERSONNE")
	private int nbrPersonne;
	@Column(name = "DMD_IS_PAT_ALLOWED")
	private Boolean isPatAllowed;
	@Column(name = "DMD_IS_CPT_ALLOWED")
	private Boolean isComptaAllowed;
	@Column(name = "DMD_IS_DRH_ALLOWED")
	private Boolean isDRHAllowed;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	Set<Accompagnant> accompagnants;
	@Column(name = "DMD_FILEPATH")
	private String filePath;
	@Column(name = "DMD_IS_VEHICULE")
	private Boolean isVehicule;
    @OneToOne(cascade=CascadeType.ALL)
    private OrdreDecaissement ordreDecaissement;
    @Column(name="IS_SUPER_ALLOWED")
    private Boolean isSuperviseurAllowed;
    @Column(name="IS_CONTROLEUR_ALLOWED")
    private Boolean isControleurAllowed;
	private static final long serialVersionUID = 1L;

	public DemandeTDR() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public String getLieu() {
		return this.lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Boolean getIsAllowed() {
		return isAllowed;
	}

	public void setIsAllowed(Boolean isAllowed) {
		this.isAllowed = isAllowed;
	}

	public String getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(String dateDebut) {
		this.dateDeb = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getSignatureDRH() {
		return signatureDRH;
	}

	public void setSignatureDRH(String signature) {
		this.signatureDRH = signature;
	}

	public int getNbrPersonne() {
		return nbrPersonne;
	}

	public void setNbrPersonne(int nbrPersonne) {
		this.nbrPersonne = nbrPersonne;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Set<ValeurRessource> getValeurRessources() {
		return valeurRessources;
	}

	public void setValeurRessources(Set<ValeurRessource> valeurRessources) {
		this.valeurRessources = valeurRessources;
	}

	public String getSignatureDGA() {
		return signatureDGA;
	}

	public void setSignatureDGA(String signatureDGA) {
		this.signatureDGA = signatureDGA;
	}

	public Boolean getIsPatAllowed() {
		return isPatAllowed;
	}

	public void setIsPatAllowed(Boolean isPatAllowed) {
		this.isPatAllowed = isPatAllowed;
	}

	public Boolean getIsComptaAllowed() {
		return isComptaAllowed;
	}

	public void setIsComptaAllowed(Boolean isComptaAllowed) {
		this.isComptaAllowed = isComptaAllowed;
	}

	public Boolean getIsDRHAllowed() {
		return isDRHAllowed;
	}

	public void setIsDRHAllowed(Boolean isDRHAllowed) {
		this.isDRHAllowed = isDRHAllowed;
	}

	public Set<Accompagnant> getAccompagnants() {
		return accompagnants;
	}

	public void setAccompagnants(Set<Accompagnant> accompagnants) {
		this.accompagnants = accompagnants;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Boolean getIsVehicule() {
		return isVehicule;
	}

	public void setIsVehicule(Boolean isVehicule) {
		this.isVehicule = isVehicule;
	}

	public OrdreDecaissement getOrdreDecaissement() {
		return ordreDecaissement;
	}

	public void setOrdreDecaissement(OrdreDecaissement ordreDecaissement) {
		this.ordreDecaissement = ordreDecaissement;
	}

	public Boolean getIsDGAllowed() {
		return isDGAllowed;
	}

	public void setIsDGAllowed(Boolean isDGAllowed) {
		this.isDGAllowed = isDGAllowed;
	}

	public Boolean getIsSuperviseurAllowed() {
		return isSuperviseurAllowed;
	}

	public void setIsSuperviseurAllowed(Boolean isSuperviseurAllowed) {
		this.isSuperviseurAllowed = isSuperviseurAllowed;
	}

	public Boolean getIsControleurAllowed() {
		return isControleurAllowed;
	}

	public void setIsControleurAllowed(Boolean isControleurAllowed) {
		this.isControleurAllowed = isControleurAllowed;
	}

}
