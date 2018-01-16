package Banque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import bibliotheque.Emprunt;
import bibliotheque.Livre;


@Entity
@Table(name="client")
public class Client {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NOM", length = 30, nullable = false)
	private String nom;
	
	@Column(name = "PRENOM", length = 30, nullable = false)
	private String prenom;
	
	@Column(name = "DATENAISSANCE", nullable = false)
	private LocalDate datenaissance;
	
	@Embedded
	private Adresse adresse;
	
	@ManyToOne
	@JoinColumn(name="ID_BANQUE")
	private Banque banque;
	
	@ManyToMany(mappedBy="clientcompte")
	private List<Compte> compte;
	

	
	
	public Client() {
		compte = new ArrayList<Compte>(0);
	}




	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}




	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}




	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}




	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}




	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}




	/**
	 * @return the datenaissance
	 */
	public LocalDate getDatenaissance() {
		return datenaissance;
	}




	/**
	 * @param datenaissance the datenaissance to set
	 */
	public void setDatenaissance(LocalDate datenaissance) {
		this.datenaissance = datenaissance;
	}




	/**
	 * @return the adresse
	 */
	public Adresse getAdresse() {
		return adresse;
	}




	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}




	/**
	 * @return the banque
	 */
	public Banque getBanque() {
		return banque;
	}




	/**
	 * @param banque the banque to set
	 */
	public void setBanque(Banque banque) {
		this.banque = banque;
	}




	/**
	 * @return the compte
	 */
	public List<Compte> getCompte() {
		return compte;
	}




	/**
	 * @param compte the compte to set
	 */
	public void setCompte(List<Compte> compte) {
		this.compte = compte;
	}




	public Client(String nom, String prenom, LocalDate datenaissance, Adresse adresse, Banque banque,
			List<Compte> compte) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.datenaissance = datenaissance;
		this.adresse = adresse;
		this.banque = banque;
		this.compte = compte;
	}
	
	

}
