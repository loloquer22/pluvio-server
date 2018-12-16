package bzh.pluvio.pluvioServer.repo;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bzh.pluvio.pluvioServer.model.RelevepluieByDate;

@Repository
public interface RelevepluieByDateRepository extends CrudRepository<RelevepluieByDate, Long> {

	@Query(value="SELECT id, date, valeur FROM relevepluie WHERE date =:date",nativeQuery=true)

	RelevepluieByDate getRelevepluieByDate(@Param("date") String date);
	
}

