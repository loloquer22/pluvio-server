package bzh.pluvio.pluvioServer.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bzh.pluvio.pluvioServer.model.MaxRainValue;



@Repository
public interface MaxRainValueRepository extends CrudRepository<MaxRainValue, Long> {
	
	@Query(value="SELECT id, date, MAX(valeur)  AS valeur FROM pluvio.relevepluie", nativeQuery=true)
	
	MaxRainValue getMaxRainValueRelevepluie();

	}