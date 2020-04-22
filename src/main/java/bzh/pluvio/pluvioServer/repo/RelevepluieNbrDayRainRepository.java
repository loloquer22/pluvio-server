package bzh.pluvio.pluvioServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bzh.pluvio.pluvioServer.model.RelevepluieNbrDayRain;

@Repository
public interface RelevepluieNbrDayRainRepository extends CrudRepository<RelevepluieNbrDayRain, Long> {

	@Query(value="SELECT annee, mois, count(*) AS nbrdays FROM relevepluie WHERE valeur>0.1 AND mois=:mois GROUP BY mois, annee", nativeQuery=true)
	
	List<RelevepluieNbrDayRain> getRelevepluieNbrDayRain(@Param("mois") int mois);
	
//	SELECT `annee`, `mois`, count(*) AS `valeur` FROM `relevepluie` WHERE `valeur`>0.1 AND `mois`=1 GROUP BY `mois`, `annee` 
//	SELECT mois, COUNT(*) AS nbrDayRain FROM relevepluie WHERE valeur>1 AND mois=2 AND annee=2007;
	//"SELECT `mois` AS `mois`, SUM(`valeur`) AS `valeur`, `annee` AS `annee` FROM `relevepluie` GROUP BY `mois`, `annee` ORDER BY `mois`, `annee`"
//	SELECT COUNT(*) AS nbrDayRain FROM relevepluie WHERE valeur>1 AND mois=:mois AND annee=:annee
	
}


