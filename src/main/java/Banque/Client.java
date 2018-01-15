package Banque;

import java.time.LocalDate;
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
	
	@Column(name = "DATENAISSANCE", length = 30, nullable = false)
	private LocalDate datenaissance;
	
	@Embedded
	private List<Adresse> adresse;
	
	@ManyToOne
	@JoinColumn(name="ID_BANQUE")
	private Banque banque;
	
	@ManyToMany(mappedBy="clientcompte")
	private List<Compte> compte;
	

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

	public Client() {
		super();
	}
	
	

}
