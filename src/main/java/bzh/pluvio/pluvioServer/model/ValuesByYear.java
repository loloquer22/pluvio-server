package bzh.pluvio.pluvioServer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relevepluie")
public class ValuesByYear {
	
	private int annee;
	private int valeur;
	
	 public ValuesByYear() {
	    }

		public ValuesByYear(int annee, int valeur) {
			this.annee = annee;
			this.valeur = valeur;
		}
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	@Override
	public String toString() {
		return String.format("ValuesByYear[annee='%s',valeur='%s' ]", annee, valeur);
	}
	
	
}
