package bzh.pluvio.pluvioServer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "relevepluie")
public class ValuesByYear {
	
	private int annee;
	private float valeur;
	
	 public ValuesByYear() {
	    }

		public ValuesByYear(int annee, float valeur) {
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
	public float getValeur() {
		return valeur;
	}
	public void setValeur(float valeur) {
		this.valeur = valeur;
	}

	@Override
	public String toString() {
		return String.format("ValuesByYear[annee='%s',valeur='%f.1' ]", annee, valeur);
	}
	
	
}
