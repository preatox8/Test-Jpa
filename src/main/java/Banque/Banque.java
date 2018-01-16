package Banque;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import bibliotheque.Emprunt;

@Entity
@Table(name="banque")
public class Banque {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NOM", length = 30, nullable = false)
	private String nom;
	
	@OneToMany(mappedBy="banque")
	private List<Client> clientbanque;
	

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

	public Banque() {
		super();
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
	 * @return the clientbanque
	 */
	public List<Client> getClientbanque() {
		return clientbanque;
	}

	/**
	 * @param clientbanque the clientbanque to set
	 */
	public void setClientbanque(List<Client> clientbanque) {
		this.clientbanque = clientbanque;
	}

	public Banque(String nom) {
		super();
		this.nom = nom;
	}
	
	

}
