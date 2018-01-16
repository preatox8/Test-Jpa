package bibliotheque;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**
 * @author ETY2
 *
 */
@Entity
@Table(name="livre")
public class Livre {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "TITRE", length = 30, nullable = false, unique = true)
	private String titre;
	
	@Column(name = "AUTEUR", length = 30, nullable = false)
	private String auteur;
	
	@ManyToMany
	@JoinTable(name="compo",
			joinColumns = @JoinColumn(name="ID_LIV", referencedColumnName="ID"),
			inverseJoinColumns = @JoinColumn(name="ID_EMP", referencedColumnName="ID")
		)
	private List<Emprunt> emprunt;

	public Livre() {
		
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
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the auteur
	 */
	public String getAuteur() {
		return auteur;
	}

	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	/**
	 * @return the emprunt
	 */
	public List<Emprunt> getEmprunt() {
		return emprunt;
	}

	/**
	 * @param emprunt the emprunt to set
	 */
	public void setEmprunt(List<Emprunt> emprunt) {
		this.emprunt = emprunt;
	}

	
}
