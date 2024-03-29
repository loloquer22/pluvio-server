package bzh.pluvio.pluvioServer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
//@Table(name = "relevepluie") 
public class RelevepluieNbrDayRain {
	
	private int annee;
	private int mois;
//	private int valeur;
	private int nbrdays;
	
	private float quantityrain;
	
	public RelevepluieNbrDayRain() {}

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}


	public int getNbrdays() {
		return nbrdays;
	}


	public void setNbrdays(int nbrdays) {
		this.nbrdays = nbrdays;
	}
	
		public float getQuantityrain() {
			return quantityrain;
		}

		public void setQuantityrain(float quantityrain) {
			this.quantityrain = quantityrain;
		}

//	public int getValeur() {
//		return valeur;
//	}
//
//	public void setValeur(int valeur) {
//		this.valeur = valeur;
//	}
	
	@Override
	public String toString() {
//		System.out.println("****  valeur "+ nbrdays);
		return String.format("RelevePluieLastValue[anne='%s', mois='%s',nbrdays='%s',quantityrain='%f.1']", annee, mois, nbrdays, quantityrain);
	}

}