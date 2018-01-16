package Banque;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import bibliotheque.Emprunt;

@Entity
@Table(name="compte")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPECOMPTE")
public class Compte {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NUMERO", length = 30)
	private String numero;
	
	@Column(name = "SOLDE", length = 30)
	private Double solde;
	
	@ManyToMany
	@JoinTable(name="corres",
			joinColumns = @JoinColumn(name="ID_COM", referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(name="ID_CLI", referencedColumnName="id")
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
		operation = new ArrayList<Operation>(0);
		clientcompte = new ArrayList<Client>(0);
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
	 * @return the clientcompte
	 */
	public List<Client> getClientcompte() {
		return clientcompte;
	}

	/**
	 * @param clientcompte the clientcompte to set
	 */
	public void setClientcompte(List<Client> clientcompte) {
		this.clientcompte = clientcompte;
	}

	/**
	 * @return the operation
	 */
	public List<Operation> getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(List<Operation> operation) {
		this.operation = operation;
	}

	public Compte(String numero, Double solde, List<Operation> operation) {
		super();
		this.numero = numero;
		this.solde = solde;
		this.operation = operation;
	}


	
	
}
