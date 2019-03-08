package bzh.pluvio.pluvioServer.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bzh.pluvio.pluvioServer.model.LastValue;
import bzh.pluvio.pluvioServer.model.Relevepluie;

@Repository
public interface LastValueRepository extends CrudRepository<LastValue, Long> {
	
	@Query(value="SELECT id, date, valeur FROM relevepluie ORDER BY id DESC LIMIT 1", nativeQuery=true)
	
		Relevepluie getLastValueRelevepluies();

	}
