package bzh.pluvio.pluvioServer.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bzh.pluvio.pluvioServer.model.Relevepluie;

@Repository
public interface RelevepluieRepository  extends CrudRepository<Relevepluie, Long> {
	
	Relevepluie findByDate(String date);

//	@Query(value="INSERT id, date, jour, mois, annee, valeur FROM relevepluie ORDER BY id DESC LIMIT 1", nativeQuery=true)
//	int postRelevePluieValue();
	
	@Query(value="UPDATE relevepluie SET id=:id, date=:date, jour=:jour, mois=:jour, annee=:annee, valeur=:valeur WHERE 1", nativeQuery=true)
	int updateRelevepluie(Relevepluie releve);
			
	
//	@Query("SELECT annee, SUM( valeur ) AS valeur FROM relevepluie WHERE annee group by annee")
//	@Query(value="SELECT annee, valeur FROM relevepluie", nativeQuery=true)
//	List<Relevepluie> getByYearRelevepluies();

// http://zetcode.com/springboot/datajpaquery/	

		
}
