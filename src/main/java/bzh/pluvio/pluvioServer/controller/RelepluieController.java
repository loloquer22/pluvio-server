package bzh.pluvio.pluvioServer.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bzh.pluvio.pluvioServer.model.ListYears;
import bzh.pluvio.pluvioServer.model.Relevepluie;
import bzh.pluvio.pluvioServer.model.RelevepluieByDate;
import bzh.pluvio.pluvioServer.model.RelevepluieNbrDayRain;
import bzh.pluvio.pluvioServer.model.RelevesPluieAutoByDay;
import bzh.pluvio.pluvioServer.model.RelevesPluieAutoByHour;
import bzh.pluvio.pluvioServer.model.TotalByMonthByYear;
import bzh.pluvio.pluvioServer.model.ValueByDayForMonthByYear;
import bzh.pluvio.pluvioServer.model.ValuesByYear;
import bzh.pluvio.pluvioServer.repo.ListYearRepository;
import bzh.pluvio.pluvioServer.repo.RelevepluieByDateRepository;
import bzh.pluvio.pluvioServer.repo.RelevepluieNbrDayRainRepository;
import bzh.pluvio.pluvioServer.repo.RelevepluieRepository;
import bzh.pluvio.pluvioServer.repo.RelevesAutoByDayRepository;
import bzh.pluvio.pluvioServer.repo.RelevesAutoByHourRepository;
import bzh.pluvio.pluvioServer.repo.TotalByMonthByYearRepository;
import bzh.pluvio.pluvioServer.repo.ValueByDayForMonthByYearRepository;
import bzh.pluvio.pluvioServer.repo.ValuesByYearRepository;

@RestController
@RequestMapping("pluvio")
@CrossOrigin(origins = "*")
public class RelepluieController {

	private static final Logger LOGGER = LogManager.getLogger(RelepluieController.class);


	@Autowired
	private RelevepluieRepository repository;

	@Autowired
	private ValuesByYearRepository valuesByYearRepository;

//	@Autowired
//	private LastValueRepository lastValueRepository;

	@Autowired
	private ListYearRepository listYearRepository;

	@Autowired
	private TotalByMonthByYearRepository totalByMonthByYearRepository;

	@Autowired
	private RelevepluieByDateRepository relevepluieByDateRepository;

	@Autowired
	private ValueByDayForMonthByYearRepository valueByDayForMonthByYearRepository;

	@Autowired
	private RelevepluieNbrDayRainRepository relevepluieNbrDayRainRepository;

	@Autowired
	private RelevesAutoByDayRepository relevesAutoByDayRepository;

	@Autowired
	private RelevesAutoByHourRepository relevesAutoByHourRepository;


	@GetMapping(value = "/relevepluie", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Relevepluie> getAll() {
		List<Relevepluie> list = new ArrayList<Relevepluie>();
		
		Iterable<Relevepluie> relevepluies = repository.findAll();
		   
//		relevepluies.forEach(list::add);
		list.addAll((Collection<? extends Relevepluie>) relevepluies);
		
		LOGGER.info(" ** relevepluie : " + relevepluies);

		return list;
	}

	@GetMapping(value = "/byYearRelevepluie", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ValuesByYear> getByYearRelevepluies(Model model) {

		List<ValuesByYear> listByYear = valuesByYearRepository.getByYearRelevepluies();
		LOGGER.info(" ** byYearRelevepluie : " + listByYear);
		model.addAttribute("listByYear", listByYear);
		return listByYear;
	}

	
	@PostMapping(value = "/addRelevepluie")
//	@PutMapping(value = "/addRelevepluie")
	public Relevepluie postRelevepluie(@RequestBody Relevepluie relevepluie) {
		LOGGER.info(" ** add relevepluie date : " + relevepluie.getDate() + " ** valeur : " + relevepluie.getValeur());

		Relevepluie releve = new Relevepluie();

		releve.setDate(relevepluie.getDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(relevepluie.getDate());
		releve.setJour(cal.get(Calendar.DAY_OF_MONTH));
		releve.setMois(cal.get(Calendar.MONTH) + 1);
		releve.setAnnee(cal.get(Calendar.YEAR));
		releve.setValeur(relevepluie.getValeur());
		LOGGER.info(" ** update value : " + relevepluie.getValeur());

		repository.save(releve);
		return relevepluie;
	}

	@GetMapping(value = "/lastValueRelevepluie", produces = MediaType.APPLICATION_JSON_VALUE)
	public Relevepluie getLastValueRelevepluies(Model model) {

		Relevepluie lastValue = repository.getLastValueRelevepluies();
		LOGGER.info(" ** lastValueRelevepluie : " + lastValue);
		model.addAttribute("Relevepluie", lastValue);
		return lastValue;

	}

	@GetMapping(value = "/listYearRelevepluie", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ListYears> getListYears(Model model) {

		List<ListYears> listYears = listYearRepository.getListYears();

		model.addAttribute("listYears", listYears);
		LOGGER.debug(" ** listYears" + listYears);

		return listYears;

	}

	@GetMapping(value = "/listValueByDayForMonthByYear/{annee}/{mois}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ValueByDayForMonthByYear>> getValueByDayForMonthByYear(
			@PathVariable(value = "annee") int annee, @PathVariable(value = "mois") int mois) throws ParseException {
		LOGGER.debug(" **request annee : " + annee + " mois : " + mois);
		if (annee == 0 && mois == 0) {
			Date date = new Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			mois = localDate.getMonthValue();
			annee = localDate.getYear();
		}
		LOGGER.info(" ** send annee : " + annee + " mois : " + mois);
		List<ValueByDayForMonthByYear> valueByDayForMonthByYear = valueByDayForMonthByYearRepository
				.getValueByDayForMonthByYear(annee, mois);
		LOGGER.info(" ** valueByDayForMonthByYear size :" + valueByDayForMonthByYear.size());
		ResponseEntity<List<ValueByDayForMonthByYear>> valByDayForMonthByYear = new ResponseEntity<List<ValueByDayForMonthByYear>>(
				valueByDayForMonthByYear, HttpStatus.OK);
		LOGGER.info(" ** valByDayForMonthByYear : " + valByDayForMonthByYear.toString());
		return valByDayForMonthByYear;
	}

	@GetMapping(value = "/relevepluieByDate/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RelevepluieByDate> getRelevepluieByDate(@PathVariable(value = "date") String date)
			throws ParseException {
		RelevepluieByDate relevepluieByDate = relevepluieByDateRepository.getRelevepluieByDate(date);
		LOGGER.info(" ** Relevepluie by date : " + date);
		ResponseEntity<RelevepluieByDate> reByDate = new ResponseEntity<RelevepluieByDate>(relevepluieByDate,
				HttpStatus.OK);
		return reByDate;
	}

	@GetMapping(value = "/totalByMonthByYear/{annee}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TotalByMonthByYear>> getTotalByMonthByYear(@PathVariable int annee) {
		List<TotalByMonthByYear> totalByMonthByYear = totalByMonthByYearRepository.getTotalByMonthByYear(annee);
		LOGGER.info(" ** list total By Month By Year : **" + annee + " size : " + totalByMonthByYear.size());
		ResponseEntity<List<TotalByMonthByYear>> reTMY = new ResponseEntity<List<TotalByMonthByYear>>(
				totalByMonthByYear, HttpStatus.OK);
		return reTMY;
	}

	@PutMapping(value = "/updateRelevepluie", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Relevepluie> putRelevepluie(@RequestBody Relevepluie relevepluie) {
		LOGGER.info(" ** update relevepluie for id= " + relevepluie.getId() + " modify by value= "
				+ relevepluie.getValeur());

		long id = relevepluie.getId();

		Optional<Relevepluie> relevepluieData = repository.findById(id);
		LOGGER.info(" ** find id relevepluieData **" + relevepluieData);
		if (relevepluieData.isPresent()) {
			Relevepluie releve = relevepluieData.get();

			releve.setValeur(relevepluie.getValeur());
			LOGGER.info(" ** update value : " + relevepluie.getValeur() + " for id : " + id);
			return new ResponseEntity<Relevepluie>(repository.save(releve), HttpStatus.OK);
		} else {
			return new ResponseEntity<Relevepluie>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "deleteRelevepluie/{id}")
	public void deleteRelevepluie(@PathVariable long id) {
		LOGGER.info(" ** delete relevepluie by id : " + id);
		repository.deleteById(id);
	}

	@GetMapping(value = "/getNbrDayTotalRain/{mois}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RelevepluieNbrDayRain>> getRelevepluieNbrDayRain(@PathVariable int mois)
			throws ParseException {
		LOGGER.info(" ** send  mois : " + mois);
		List<RelevepluieNbrDayRain> valueRelevepluieNbrDayRain = relevepluieNbrDayRainRepository
				.getRelevepluieNbrDayRain(mois);
		LOGGER.info(" ** valueRelevepluieNbrDayRain size :" + valueRelevepluieNbrDayRain.size());
		ResponseEntity<List<RelevepluieNbrDayRain>> respRelevepluieNbrDayRain = new ResponseEntity<List<RelevepluieNbrDayRain>>(
				valueRelevepluieNbrDayRain, HttpStatus.OK);
//		LOGGER.info(" ** respRelevepluieNbrDayRain : " + respRelevepluieNbrDayRain);
		return respRelevepluieNbrDayRain;
	}

//	@GetMapping(value = "/getRelevepluieNbrDayRain", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<RelevepluieNbrDayRain>> getRelevepluieNbrDayRain(Model model) throws ParseException {
//		List<RelevepluieNbrDayRain> valueRelevepluieNbrDayRainData = relevepluieNbrDayRainRepository.getRelevepluieNbrDayRain();
//		LOGGER.info(" ** valueRelevepluieNbrDayRainData  :" + valueRelevepluieNbrDayRainData );
//		
//		model.addAttribute("listRelevepluieNbrDayRain", valueRelevepluieNbrDayRainData);
//		LOGGER.debug(" ** listRelevepluieNbrDayRain" + valueRelevepluieNbrDayRainData );
//		
//		ResponseEntity<List<RelevepluieNbrDayRain>> listRelevepluieNbrDayRainData= new ResponseEntity<List<RelevepluieNbrDayRain>>(valueRelevepluieNbrDayRainData, HttpStatus.OK);
////		ResponseEntity<RelevepluieNbrDayRain> respRelevepluieNbrDayRain= new ResponseEntity<RelevepluieNbrDayRain>(valueRelevepluieNbrDayRain, HttpStatus.OK);
////		return respRelevepluieNbrDayRain;
//		return null;
//	}

	@GetMapping(value = "/getListByDayAuto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RelevesPluieAutoByDay>> getAllAutoByDay() throws ParseException {

		List<RelevesPluieAutoByDay> valueRelevespluieAutoByDay = relevesAutoByDayRepository.getListAutoByDay();

		ResponseEntity<List<RelevesPluieAutoByDay>> respRelevesPluieAutoByDay = new ResponseEntity<List<RelevesPluieAutoByDay>>(
				valueRelevespluieAutoByDay, HttpStatus.OK);

		LOGGER.info(" ** relevespluieautoByDay : " + respRelevesPluieAutoByDay);

		return respRelevesPluieAutoByDay;
	}

	@GetMapping(value = "/getListByHourAuto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RelevesPluieAutoByHour>> getAllAutoByHour() throws ParseException {

		List<RelevesPluieAutoByHour> valueRelevespluieAutoByHour = relevesAutoByHourRepository.getListAutoByHour();
		
		LOGGER.info(" ** valueRelevespluieAutoByHour  :" + valueRelevespluieAutoByHour.size() );

		List<RelevesPluieAutoByHour> hourValue = new ArrayList<RelevesPluieAutoByHour>();
				
				for (int e = 0; e <= 23; e++) {
					RelevesPluieAutoByHour obj = new RelevesPluieAutoByHour();
				 obj.setHour(e);
				 obj.setValue(0);
				 hourValue.add(obj);
				 }

				LOGGER.info(" hourValue "+ hourValue);
				
				for (RelevesPluieAutoByHour nHv : hourValue ) {
					LOGGER.debug( " nHv hour:  " + nHv.getHour( ) + " value :  " + nHv.getValue( ) );
				       boolean firstPassage= false;
				      
				      for (RelevesPluieAutoByHour elmt : valueRelevespluieAutoByHour ) {
				        if (nHv.getHour( ) == elmt.getHour( ) && elmt.getValue( ) > 0 ) {
				          hourValue.set(nHv.getHour( ), elmt);
				          firstPassage= true;
				        }
				        }
				      	if (!firstPassage) {
				      		hourValue.set(nHv.getHour( ),nHv );
				      }
				    }      
		
		ResponseEntity<List<RelevesPluieAutoByHour>> 	 respRelevesPluieAutoByHour = new ResponseEntity<List<RelevesPluieAutoByHour>>(hourValue ,HttpStatus.OK);
		return respRelevesPluieAutoByHour;
	}

}
