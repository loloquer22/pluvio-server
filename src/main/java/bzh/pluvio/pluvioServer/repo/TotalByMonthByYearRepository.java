package bzh.pluvio.pluvioServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bzh.pluvio.pluvioServer.model.TotalByMonthByYear;

@Repository
public interface TotalByMonthByYearRepository extends CrudRepository<TotalByMonthByYear, Long> {

	@Query(value="SELECT annee, mois, SUM( valeur ) AS valeur FROM relevepluie WHERE annee =:annee group by mois",nativeQuery=true)

	List<TotalByMonthByYear> getTotalByMonthByYear(@Param("annee") int annee);
	
}
