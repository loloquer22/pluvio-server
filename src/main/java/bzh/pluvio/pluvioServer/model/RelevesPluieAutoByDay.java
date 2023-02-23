package bzh.pluvio.pluvioServer.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "relevepluieauto")
public class RelevesPluieAutoByDay implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	@Id
//	@Temporal(TemporalType.DATE)
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "fr_FR", timezone = "UTC")
	
	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id", updatable = false, nullable = false)
//	private long id;

//	@Column(name = "date")
//	@JsonFormat(pattern="yyyy-MM-dd")
	private String date;

//	@Column(name = "value")
	private int value;
	
	public RelevesPluieAutoByDay() {}
	
//	public long getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public RelevesPluieAutoByDay(String date,  int value) {
		this.date = date;
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format("RelevesPluieAutoDay[date='%s,value='%d' ]", date, value);
	}

}
