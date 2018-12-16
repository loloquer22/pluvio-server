package bzh.pluvio.pluvioServer.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import bzh.pluvio.pluvioServer.model.TotalByMonthByYear;
import bzh.pluvio.pluvioServer.model.ValuesByYear;
import bzh.pluvio.pluvioServer.repo.LastValueRepository;
import bzh.pluvio.pluvioServer.repo.ListYearRepository;
import bzh.pluvio.pluvioServer.repo.RelevepluieByDateRepository;
import bzh.pluvio.pluvioServer.repo.RelevepluieRepository;
import bzh.pluvio.pluvioServer.repo.TotalByMonthByYearRepository;
import bzh.pluvio.pluvioServer.repo.ValuesByYearRepository;

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
	
	@GetMapping(value = "/relevepluie", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Relevepluie> getAll() {
		List<Relevepluie> list = new ArrayList<>();
		Iterable<Relevepluie> relevepluies = repository.findAll();

		relevepluies.forEach(list::add);
		logger.info(" ** relevepluie : "+ relevepluies);
		
		return list;
	}

	@GetMapping(value = "/byYearRelevepluie", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ValuesByYear> getByYearRelevepluies(Model model) {

		List<ValuesByYear> listByYear = valuesByYearRepository.getByYearRelevepluies();
		logger.info(" ** byYearRelevepluie : "+ listByYear);
		model.addAttribute("listByYear", listByYear);
		return listByYear;

	}
	
	@PostMapping(value = "/addRelevepluie")
	public Relevepluie postRelevepluie(@RequestBody Relevepluie relevepluie) {
		logger.info(" ** add relevepluie date : " + relevepluie.getDate()+ " ** valeur : " + relevepluie.getValeur());
		
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
	public Relevepluie getLastValueRelevepluies(Model model) {

		Relevepluie lastValue = repository.getLastValueRelevepluies();
		logger.info(" ** lastValueRelevepluie : "+ lastValue);
		model.addAttribute("Relevepluie", lastValue);
		return lastValue;

	}

	@GetMapping(value = "/listYearRelevepluie", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ListYears> getListYears(Model model) {

		List<ListYears> listYears = listYearRepository.getListYears();

		model.addAttribute("listYears", listYears);
		logger.debug(" ** listYears" + listYears );
		
		return listYears;

	}

	
	@GetMapping(value = "/relevepluieByDate/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<RelevepluieByDate> getRelevepluieByDate(@PathVariable(value = "date") String date) throws ParseException{
		RelevepluieByDate relevepluieByDate = relevepluieByDateRepository.getRelevepluieByDate(date);
		logger.info(" ** Relevepluie by date : " + date );
		
		ResponseEntity<RelevepluieByDate> reByDate= new ResponseEntity<RelevepluieByDate>(relevepluieByDate, HttpStatus.OK);
    
		return reByDate;
	}
				
	
	@GetMapping(value = "/totalByMonthByYear/{annee}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TotalByMonthByYear>> getTotalByMonthByYear(@PathVariable int annee) {
		
		List<TotalByMonthByYear> totalByMonthByYear = totalByMonthByYearRepository.getTotalByMonthByYear(annee );

		logger.info(" ** list total By Month By Year : **" + annee + " size : " +  totalByMonthByYear.size());
		ResponseEntity<List<TotalByMonthByYear>> reTMY= new ResponseEntity<List<TotalByMonthByYear>>(totalByMonthByYear, HttpStatus.OK);
    
		return reTMY;
	}
	
	@PutMapping(value = "updateRelevepluie")
	public ResponseEntity<Relevepluie> putRelevepluie(@RequestBody Relevepluie relevepluie){ 
		logger.info(" ** update relevepluie for id= " + relevepluie.getId()  +  " modify by value= " + relevepluie.getValeur() );
		
		long id =  relevepluie.getId();
		int valeur = relevepluie.getValeur();
	
		Optional<Relevepluie> relevepluieData = repository.findById(id);
		
		logger.info(" ** find id relevepluieData **" + relevepluieData);
		if ( relevepluieData.isPresent()) {
		
		Relevepluie releve = relevepluieData.get();
		releve.setDate(relevepluie.getDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(relevepluie.getDate());
		releve.setJour(cal.get(Calendar.DAY_OF_MONTH));
		releve.setMois(cal.get(Calendar.MONTH)+1);
		releve.setAnnee(cal.get(Calendar.YEAR));
		releve.setValeur(relevepluie.getValeur());
			logger.info(" ** update value : " + valeur + " for id : " + id);
		return  new ResponseEntity<>(repository.save(releve), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	
	@DeleteMapping(value = "deleteRelevepluie/{id}")
	public void deleteRelevepluie(@PathVariable long id){
		 logger.info(" ** delete relevepluie by id : " + id );
		repository.delete(id);
	}

	
}
