package bzh.pluvio.pluvioServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bzh.pluvio.pluvioServer.model.ListValDayMonthYear;

@Repository
public interface ListValDayMthYearRepository extends CrudRepository<ListValDayMonthYear, Long> {

	@Query(value="SELECT jour, valeur FROM relevepluie WHERE annee = :annee and mois = :mois", nativeQuery=true)
	List<ListValDayMonthYear> getValDayMonthYear( @Param("annee") Integer annee, @Param("mois") Integer mois);

}
 