package bzh.pluvio.pluvioServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bzh.pluvio.pluvioServer.model.ValuesByYear;


@Repository
public interface ValuesByYearRepository extends CrudRepository<ValuesByYear, Long> {

		
	@Query(value="SELECT id, annee, SUM( valeur ) AS valeur FROM relevepluie WHERE annee group by annee", nativeQuery=true)
	List<ValuesByYear> getByYearRelevepluies();
	
//	@Query(value="SELECT id, date, valeur	FROM relevepluie ORDER BY id DESC LIMIT 1", nativeQuery=true)
//	RelevePluieLastValue getLastValueRelevepluies();
//	
//	@Query(value="SELECT DISTINCT annee FROM relevepluie ORDER BY annee",nativeQuery=true)
//	List<ListYears>  getListYears();
//	
//	@Query(value="SELECT annee, mois, SUM( valeur ) AS valeur FROM relevepluie WHERE annee =${annee} group by mois",nativeQuery=true)
//	TotalByMonthByYear getTotalByMonthByYear();
//	
}