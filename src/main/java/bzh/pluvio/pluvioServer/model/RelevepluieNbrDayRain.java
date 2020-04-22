package bzh.pluvio.pluvioServer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relevepluie") 
public class RelevepluieNbrDayRain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int annee;
	private int mois;
	private int nbrdays;
	
	public RelevepluieNbrDayRain() {}

	
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

}