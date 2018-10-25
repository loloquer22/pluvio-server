package bzh.pluvio.pluvioServer.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bzh.pluvio.pluvioServer.model.LastValue;
import bzh.pluvio.pluvioServer.model.ListYears;
import bzh.pluvio.pluvioServer.model.Relevepluie;
import bzh.pluvio.pluvioServer.model.RelevepluieByDate;
import bzh.pluvio.pluvioServer.model.TotalByMonthByYear;
import bzh.pluvio.pluvioServer.model.ValuesByYear;
import bzh.pluvio.pluvioServer.repo.LastValueRepository;
import bzh.pluvio.pluvioServer.repo.ListYearRepository;
import bzh.pluvio.pluvioServer.repo.RelevepluieByDateRepository;
import bzh.pluvio.pluvioServer.repo.RelevepluieRepository;
import bzh.pluvio.pluvioServer.repo.TotalByMonthByYearRepository;
import bzh.pluvio.pluvioServer.repo.ValuesByYearRepository;

@CrossOrigin(origins = *, maxAge = 3000)
@RestController
@RequestMapping("pluvio")
public class RelepluieController {
	
	private static final Logger logger = LoggerFactory.getLogger(RelepluieController.class);


	@Autowired
	RelevepluieRepository repository;

	@Autowired
	ValuesByYearRepository valuesByYearRepository;

	@Autowired
	LastValueRepository lastValueRepository;

	@Autowired
	ListYearRepository listYearRepository;

	@Autowired
	TotalByMonthByYearRepository totalByMonthByYearRepository;
	
	@Autowired
	RelevepluieByDateRepository relevepluieByDateRepository;
	
	// @Autowired
	// IRelevepluieService relevepluieService;


	@GetMapping(value = "/relevepluie", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Relevepluie> getAll() {
		List<Relevepluie> list = new ArrayList<>();
		Iterable<Relevepluie> relevepluies = repository.findAll();

		relevepluies.forEach(list::add);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set( "Access-Control-Allow-Origin", "*");
		logger.info(" ** Relevepluie by date **" + responseHeaders); 
		
		return list;
	}

	@GetMapping(value = "/byYearRelevepluie", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ValuesByYear> getByYearRelevepluies(Model model) {

		List<ValuesByYear> listByYear = valuesByYearRepository.getByYearRelevepluies();

		model.addAttribute("listByYear", listByYear);
		return listByYear;

	}
	
	@PostMapping(value = "/addRelevepluie")
	public Relevepluie postRelevepluie(@RequestBody Relevepluie relevepluie) {
		logger.info("**add relevepluie date : " + relevepluie.getDate()+ "**valeur : " + relevepluie.getValeur());
		
		Relevepluie releve = new Relevepluie();
		
		releve.setDate(relevepluie.getDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(relevepluie.getDate());
		releve.setJour(cal.get(Calendar.DAY_OF_MONTH));
		releve.setMois(cal.get(Calendar.MONTH)+1);
		releve.setAnnee(cal.get(Calendar.YEAR));
		releve.setValeur(relevepluie.getValeur());
		
		repository.save(releve);
		return relevepluie;
	}

	@GetMapping(value = "/lastValueRelevepluie", produces = MediaType.APPLICATION_JSON_VALUE)
	public LastValue getLastValueRelevepluies(Model model) {

		LastValue lastValue = lastValueRepository.getLastValueRelevepluies();
		logger.info("**** "+ lastValue);
		model.addAttribute("lastValue", lastValue);
		return lastValue;

	}

	@GetMapping(value = "/listYearRelevepluie", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ListYears> getListYears(Model model) {

		List<ListYears> listYears = listYearRepository.getListYears();

		model.addAttribute("listYears", listYears);
		logger.debug("**** listYears" + listYears );
		
		return listYears;

	}

	
	@GetMapping(value = "/relevepluieByDate/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<RelevepluieByDate>> getRelevepluieByDate(@PathVariable(value = "date") String date) throws ParseException{
		List<RelevepluieByDate> relevepluieByDate = relevepluieByDateRepository.getRelevepluieByDate(date);
		logger.info(" ** Relevepluie by date **" + date + " ** number date find :" + relevepluieByDate.size());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set( "Access-Control-Allow-Origin", "*");
		logger.info(" ** Relevepluie by date **" + responseHeaders);
		ResponseEntity<List<RelevepluieByDate>> reByDate= new ResponseEntity<List<RelevepluieByDate>>(relevepluieByDate, responseHeaders, HttpStatus.OK);
    
		return reByDate;
	}
				
	
	@GetMapping(value = "/totalByMonthByYear/{annee}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TotalByMonthByYear>> getTotalByMonthByYear(@PathVariable int annee) {
		List<TotalByMonthByYear> totalByMonthByYear = totalByMonthByYearRepository.getTotalByMonthByYear(annee );

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set( "Access-Control-Allow-Origin", "*");
		logger.info(" ** size list total By Month By Year **" + totalByMonthByYear.size() + " ** list Total By Month By Year **" + responseHeaders);
		ResponseEntity<List<TotalByMonthByYear>> reTMY= new ResponseEntity<List<TotalByMonthByYear>>(totalByMonthByYear, responseHeaders, HttpStatus.OK);
    
		return reTMY;
	}
	
	@GetMapping(value = "updateRelevepluie", produces = MediaType.APPLICATION_JSON_VALUE)
	public void putRelevepluie(@RequestBody Relevepluie relevepluie){ 
		Relevepluie releve = new Relevepluie();
		logger.info("**update relevepluie by id= " + relevepluie.getId() +  "for value= " + relevepluie.getValeur() );
		releve.setId(relevepluie.getId());
		releve.setValeur(relevepluie.getValeur());
		
		repository.updateRelevepluie(releve);
		return;
	}
	

	
	@GetMapping(value = "deleteRelevepluie/{id}")
	public void deleteRelevepluie(@PathVariable Long id){
		 logger.info("**delete relevepluie by id : " + id );
		repository.delete(id);
	}

//	@GetMapping(value = "/totalByMonthByYear/{annee}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<TotalByMonthByYear> getTotalByMonthByYear(Model model ) {
//		
//		int annee =2014;
//		List<TotalByMonthByYear> totalByMonthByYear = totalByMonthByYearRepository.getTotalByMonthByYear(annee );
//		System.out.println("**** totalByMonthByYear " + totalByMonthByYear);
//
//		model.addAttribute("totalByMonthByYear", totalByMonthByYear);
//		return totalByMonthByYear;
//	}
	
//	@GetMapping(value = "/totalByMonthByYear/{annee}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<TotalByMonthByYear> getTotalByMonthByYear(@PathVariable String annee ) {
//		
//		logger.debug("**** annee " + annee );
//		System.out.println("**** annee " + annee);
//
//		List<TotalByMonthByYear> totalByMonthByYear = totalByMonthByYearRepository.getTotalByMonthByYear(annee);
//		System.out.println("**** totalByMonthByYear " + totalByMonthByYear);
//
//		return totalByMonthByYear;
//
//	}
	
}
		
//		@GetMapping(value = "/totalByMonthByYear/{annee}", produces = MediaType.APPLICATION_JSON_VALUE)
//		public ResponseEntity<List<TotalByMonthByYear>> getTotalByMonthByYear(@PathVariable String annee ) {
//			
//			logger.debug("**** annee " + annee );
//			System.out.println("**** annee " + annee);
//
//			List<TotalByMonthByYear> totalByMonthByYear = totalByMonthByYearRepository.getTotalByMonthByYear(annee);
//			System.out.println("**** totalByMonthByYear " + totalByMonthByYear);
//
//			return new ResponseEntity<List<TotalByMonthByYear>>(totalByMonthByYear, HttpStatus.OK);
////			return totalByMonthByYear;
//
//		}

	
