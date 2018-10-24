package bzh.pluvio.pluvioServer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relevepluie")
public class TotalByMonthByYear {
	
	private int annee;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mois;
	private int valeur;
	
	
	public TotalByMonthByYear() {}
	
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
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	@Override
	public String toString() {
		return String.format("TotalByMonthByYear[annee='%s',mois='%s',valeur='%s' ]",annee, mois, valeur);
	}

}
