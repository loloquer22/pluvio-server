package bzh.pluvio.pluvioServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bzh.pluvio.pluvioServer.model.RelevesPluieAutoByHour;

public interface RelevesAutoByHourRepository extends CrudRepository<RelevesPluieAutoByHour, Long> {

	@Query(value="SELECT COUNT(*) AS value, HOUR(date)  AS hour FROM relevepluieauto WHERE date>=CURRENT_DATE -5 GROUP BY HOUR(date)", nativeQuery=true)

//	SELECT SUM(value) AS value, HOUR(date) AS hour FROM relevepluieauto WHERE date>=CURRENT_DATE -1 GROUP BY HOUR(date)
	//	SELECT SUM(value), DATE(date) DateOnly FROM relevepluieauto GROUP BY DateOnly;
	// SELECT value, date FROM relevepluieauto WHERE date>=CURRENT_DATE GROUP BY HOUR(date)
//	SELECT COUNT(*) AS value, date FROM relevepluieauto WHERE date>=CURRENT_DATE GROUP BY HOUR(date)
	// SELECT value, HOUR(date) AS hour FROM relevepluieauto WHERE date>=CURRENT_DATE -1 GROUP BY HOUR(date)
	// SELECT SUM(value), HOUR(date) AS hour FROM relevepluieauto WHERE date>=CURRENT_DATE -1 GROUP BY HOUR(date)


	
	List<RelevesPluieAutoByHour> getListAutoByHour();

}
