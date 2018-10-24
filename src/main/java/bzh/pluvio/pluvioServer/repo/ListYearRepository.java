package bzh.pluvio.pluvioServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bzh.pluvio.pluvioServer.model.ListYears;

@Repository
public interface ListYearRepository extends CrudRepository<ListYears, Long> {

	@Query(value="SELECT DISTINCT annee FROM relevepluie ORDER BY annee",nativeQuery=true)
	List<ListYears>  getListYears();

	
}
