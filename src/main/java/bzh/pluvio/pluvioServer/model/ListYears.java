package bzh.pluvio.pluvioServer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
