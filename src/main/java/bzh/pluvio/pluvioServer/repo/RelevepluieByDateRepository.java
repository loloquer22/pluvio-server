package bzh.pluvio.pluvioServer.repo;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bzh.pluvio.pluvioServer.model.RelevepluieByDate;

@Repository
public interface RelevepluieByDateRepository extends CrudRepository<RelevepluieByDate, Long> {

	@Query(value="SELECT id, annee, date, valeur FROM relevepluie WHERE date =:date",nativeQuery=true)

	List<RelevepluieByDate> getRelevepluieByDate(@Param("date") String date);
	
}

