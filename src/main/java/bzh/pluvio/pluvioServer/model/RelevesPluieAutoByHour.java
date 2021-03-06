package bzh.pluvio.pluvioServer.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relevepluieauto")
public class RelevesPluieAutoByHour implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	@Id
//	@Temporal(TemporalType.DATE)
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh", locale = "fr_FR", timezone = "UTC")
	
	private int hour;

	private int value;
	
	public RelevesPluieAutoByHour() {}
	
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public RelevesPluieAutoByHour(int hour,  int value) {
		this.hour = hour;
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format("RelevesPluieAutoByHour[hour='%d,value='%d' ]", hour, value);
	}

}
