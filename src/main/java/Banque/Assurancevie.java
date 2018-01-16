package Banque;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author ETY2
 *
 */
@Entity
@DiscriminatorValue("AssVie")
public class Assurancevie extends Compte{

	@Column(name = "TAUX")
	private Double taux;
	
	@Column(name = "DATEFIN")
	private LocalDate datefin;

	/**
	 * @return the taux
	 */
	public Double getTaux() {
		return taux;
	}

	/**
	 * @param taux the taux to set
	 */
	public void setTaux(Double taux) {
		this.taux = taux;
	}

	/**
	 * @return the datefin
	 */
	public LocalDate getDatefin() {
		return datefin;
	}

	/**
	 * @param datefin the datefin to set
	 */
	public void setDatefin(LocalDate datefin) {
		this.datefin = datefin;
	}

	public Assurancevie() {
		super();
	}
	
	

}
