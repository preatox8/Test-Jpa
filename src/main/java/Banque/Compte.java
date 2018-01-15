package Banque;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import bibliotheque.Emprunt;

@Entity
@Table(name="compte")
public class Compte {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NUMERO", length = 30, nullable = false)
	private String numero;
	
	@Column(name = "SOLDE", length = 30, nullable = false)
	private Double solde;
	
	@ManyToMany
	@JoinTable(name="corres",
			joinColumns = @JoinColumn(name="ID_COM", referencedColumnName="ID"),
			inverseJoinColumns = @JoinColumn(name="ID_CLI", referencedColumnName="ID")
		)
	private List<Client> clientcompte;
	
	@OneToMany(mappedBy="compte")
	private List<Operation> operation;

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the solde
	 */
	public Double getSolde() {
		return solde;
	}

	/**
	 * @param solde the solde to set
	 */
	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public Compte() {
		super();
	}
	
	
}
