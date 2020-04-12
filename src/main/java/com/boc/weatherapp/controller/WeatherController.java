package com.boc.weatherapp.controller;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.boc.weatherapp.climateSummaryException.ClimateDetailDateFormatException;
import com.boc.weatherapp.climateSummaryException.ClimateDetailNotFoundException;
import com.boc.weatherapp.climateSummaryException.ClimateSummaryCustomException;
import com.boc.weatherapp.model.ClimateSummary;
import com.boc.weatherapp.repo.ClimateDataRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Main Controller object to handle incoming request 
 * */
@Controller
public class WeatherController {

	@Autowired
	JobLauncher launcher;
	
	@Autowired
	Job job;
	
	@Autowired
	ClimateDataRepo dataRepo;
	
	private List<ClimateSummary> lstClimateSummary;
	Logger log = LoggerFactory.getLogger(this.getClass());
	private int jobCount = 0;
	
	@PostConstruct
    public void init() throws Exception {	
		lstClimateSummary = new ArrayList<ClimateSummary>();
	}
	
	/*
	 * Purpose: The index controller handles job to read and write database data from csv file. It then redirects user to 
	 * 			/climate page where data is represented in a table. 
	 * Output: redirects user to /climate page where all climate summary has been displayed
	 * */
	@GetMapping
	public String index() {
		try {
			if(jobCount==0) {
				log.info("BOC Climate Weather started on localhost 8080");
				Map<String, JobParameter> maps = new HashMap<>();
				maps.put("time", new JobParameter(System.currentTimeMillis()));
				JobParameters parameters = new JobParameters(maps);
				log.info("H2 Database Upload started");
				JobExecution jobExecution = launcher.run(job, parameters);
				log.info("H2 Database Upload finished successfully.");
				
				jobCount+=1;
			}
			log.info("Redirecting user to homepage.");
		}
		catch(JobExecutionAlreadyRunningException ex) {log.error(ex.getMessage());}
		catch(JobRestartException ex) {log.error(ex.getMessage());}
		catch(JobInstanceAlreadyCompleteException ex) {log.error(ex.getMessage());}
		catch(JobParametersInvalidException ex) {log.error(ex.getMessage());}
		return "redirect:/climate";
	}
	  
	@GetMapping("/climate")
	public String getClimateSummary(Model model) {
		lstClimateSummary = dataRepo.findAll();
		model.addAttribute("climates", lstClimateSummary);
	    return "climateSummary";
	}
	
	/*
	 * Purpose: Handles post method from user to query data based on the date range and returns the appropriate template.
	 * Output: redirects user to /climate page where all climate summary has been displayed
	 * */
	@RequestMapping(value = "/climateSummaryFilterByDate", method=RequestMethod.POST)
	public String getClimateSummaryFilterByDate(@RequestParam("dateFrom") String dateFrom,
												@RequestParam("dateTo") String dateTo, Model model) throws ParseException
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date from, to;
		try{from = df.parse(dateFrom);}
		catch(ParseException ex) {
			log.error("Date format exception for dateFrom parameter : " + ex.getMessage() );
			throw new ClimateDetailDateFormatException();}
		try {to = df.parse(dateTo);}
		catch(ParseException ex) {
			log.error("Date format exception for dateTo parameter : " + ex.getMessage() );
			throw new ClimateDetailDateFormatException();}
		if (from.compareTo(to) <= 0) {
			lstClimateSummary = dataRepo.getAllBetweenDates(from, to);
			model.addAttribute("climates",lstClimateSummary);
			return "climateSummary";
		}
		else {log.error("Date exception From date needs to be older");
			throw new ClimateSummaryCustomException();}
	}
	
	/*
	 * Purpose: displays detail climate summary and all the properties
	 * Output: redirects user to detail page where detial about climate summary is displayed.
	 * */
	@RequestMapping(value = "/climatedetail", method=RequestMethod.GET)
	public String getClimateDetail(@RequestParam("id") String id, Model model) throws NumberFormatException {
		int climateId;
		try {
			climateId = Integer.parseInt(id);
		}
		catch(NumberFormatException ex) {
			log.error("Climate Id parse error for id : " + id + " " + ex.getMessage());
			throw new ClimateDetailNotFoundException();}
		model.addAttribute("climateDetail", dataRepo.findById(climateId).orElseThrow(ClimateDetailNotFoundException::new));
		return "climateDetail";
	}
}
