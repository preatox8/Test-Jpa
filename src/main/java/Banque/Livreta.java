package Banque;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LivA")
public class Livreta extends Compte{

	@Column(name = "TAUX")
	private Double taux;
	
	

	public Livreta() {
		super();
	}


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
	
	

}
