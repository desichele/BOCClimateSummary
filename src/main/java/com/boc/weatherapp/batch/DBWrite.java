package com.boc.weatherapp.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boc.weatherapp.model.ClimateSummary;
import com.boc.weatherapp.repo.ClimateDataRepo;

@Component
public class DBWrite implements ItemWriter<ClimateSummary> {
	
	/*
	 * Main class to execute
	 * */
	@Autowired
	private ClimateDataRepo climateDataRepo;
	
	@Override
	public void write(List<? extends ClimateSummary> items) throws Exception {
		// TODO Auto-generated method stub
		climateDataRepo.saveAll(items);
	}

}
