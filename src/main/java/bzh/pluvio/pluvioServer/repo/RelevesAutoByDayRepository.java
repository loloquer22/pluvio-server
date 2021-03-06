package bzh.pluvio.pluvioServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bzh.pluvio.pluvioServer.model.RelevesPluieAutoByDay;

public interface RelevesAutoByDayRepository extends CrudRepository<RelevesPluieAutoByDay, Long> {

//	@Query(value="SELECT count(*) AS value, date FROM relevepluieauto WHERE date>= DATE GROUP BY DATE(date) DESC limit 31", nativeQuery=true)
	@Query(value="SELECT COUNT(*) AS value, DATE(`date`) AS date FROM relevepluie WHERE TIME(`date`) >=  DATE GROUP BY DATE(`date`) DESC limit 10", nativeQuery=true)

	//	SELECT SUM(value), DATE(date) DateOnly FROM relevepluieauto GROUP BY DateOnly;
	// SELECT value, date FROM relevepluieauto WHERE date>=CURRENT_DATE GROUP BY HOUR(date)
//	SELECT COUNT(*) AS value, date FROM relevepluieauto WHERE date>=CURRENT_DATE GROUP BY HOUR(date)
	// SELECT value, HOUR(date) AS hour FROM relevepluieauto WHERE date>=CURRENT_DATE -1 GROUP BY HOUR(date)
	// SELECT SUM(value), HOUR(date) AS hour FROM relevepluieauto WHERE date>=CURRENT_DATE -1 GROUP BY HOUR(date)
	// SELECT COUNT(*), DATE(`date`) FROM PluvioAuto.Releves WHERE TIME(`date`) >=  DATE GROUP BY DATE(`date`) DESC limit 10;
//	SELECT COUNT(*) AS value, date FROM relevepluieauto WHERE date>=CURRENT_DATE GROUP BY DATE(date)
//	SELECT COUNT(*) AS value, date FROM relevepluieauto WHERE date>= DATE GROUP BY DATE(date) DESC limit 10;
//	SELECT COUNT(*), DATE_FORMAT(`date`, "%d-%m-%Y") AS date  FROM PluvioAuto.Releves WHERE TIME(`date`) >=  DATE GROUP BY DATE(`date`) DESC limit 10;

//	SELECT COUNT(*) AS value, DATE_FORMAT(`date`, "%Y-%m-%d") as date_auto FROM relevepluieauto GROUP BY date_auto;
//	SELECT COUNT(*) AS value, DATE_FORMAT(`date`, "%Y-%m-%d") as date_auto FROM relevepluieauto GROUP BY date_auto DESC LIMIT 10 
//	SELECT COUNT(*), DATE_FORMAT(Created_At,"%Y-%m-%d") as Created_Day1 FROM Mydate1 GROUP BY Created_Day1
	
	List<RelevesPluieAutoByDay> getListAutoByDay();

}
