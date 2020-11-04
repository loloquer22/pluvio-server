package bzh.pluvio.pluvioServer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "relevepluie") 
public class RelevepluieNbrDayRain {
	
	private int annee;
	private int mois;
//	private int valeur;
	private int nbrdays;
	
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
		return String.format("RelevePluieLastValue[anne='%s', mois='%s',nbrdays='%s' ]", annee, mois, nbrdays);
	}

}