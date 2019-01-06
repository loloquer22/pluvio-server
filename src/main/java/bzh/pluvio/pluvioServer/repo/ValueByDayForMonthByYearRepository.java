package bzh.pluvio.pluvioServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bzh.pluvio.pluvioServer.model.ValueByDayForMonthByYear;

@Repository
public interface ValueByDayForMonthByYearRepository extends CrudRepository<ValueByDayForMonthByYear, Long> {

	@Query(value = "SELECT annee, mois, jour, valeur FROM relevepluie WHERE annee =:annee AND mois=:mois", nativeQuery = true)

	List<ValueByDayForMonthByYear> getValueByDayForMonthByYear(@Param("annee") int annee, @Param("mois") int mois)
;
}