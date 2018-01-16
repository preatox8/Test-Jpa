package bibliotheque;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * @author ETY2
 *
 */
@Entity
@Table(name="client")
public class Clientbibliotheque {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NOM", length = 30, nullable = false, unique = true)
	private String nom;
	
	@Column(name = "PRENOM", length = 30, nullable = false)
	private String prenom;
	
	@OneToMany(mappedBy="client")
	private List<Emprunt> emprunt1;
	
	public Clientbibliotheque() {
		
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
	 * @return the emprunt
	 */
	public List<Emprunt> getEmprunt() {
		return emprunt1;
	}

	/**
	 * @param emprunt the emprunt to set
	 */
	public void setEmprunt(List<Emprunt> emprunt) {
		this.emprunt1 = emprunt;
	}
	
	
	
}
