package Banque;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public class Adresse {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NUMERO", length = 10, nullable = false)
	private Integer numero;
	
	@Column(name = "RUE", length = 30, nullable = false)
	private String rue;
	
	@Column(name = "CODEPOSTAL", length = 5, nullable = false)
	private Integer codepostal;
	
	@Column(name = "VILLE", length = 30, nullable = false)
	private String ville;

	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * @return the codepostal
	 */
	public Integer getCodepostal() {
		return codepostal;
	}

	/**
	 * @param codepostal the codepostal to set
	 */
	public void setCodepostal(Integer codepostal) {
		this.codepostal = codepostal;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	public Adresse() {
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
	
	

}
