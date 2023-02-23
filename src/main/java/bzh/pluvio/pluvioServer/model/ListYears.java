package bzh.pluvio.pluvioServer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "relevepluie")
public class ListYears {
	
	private int annee;

	public ListYears() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	@Override
	public String toString() {
		return String.format("ListYears[annee='%s']", annee);
	}
}
