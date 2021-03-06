package bzh.pluvio.pluvioServer.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bzh.pluvio.pluvioServer.model.Relevepluie;

@Repository
public interface RelevepluieRepository  extends CrudRepository<Relevepluie, Long> {
	
	Relevepluie findByDate(String date);

	@Query(value="SELECT id, date ,annee, mois, jour, valeur FROM relevepluie ORDER BY id DESC LIMIT 1", nativeQuery=true)
	Relevepluie getLastValueRelevepluies();

	Optional<Relevepluie> findById(long id);
		
}
