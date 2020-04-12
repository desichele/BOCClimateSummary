package com.boc.weatherapp.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.boc.weatherapp.model.ClimateSummary;

@Component
public class Processor implements ItemProcessor<ClimateSummary, ClimateSummary>{

	/*
	 * This processor is designated to handle null values before writing to Database.
	 * Processing mode data as part of validation. 
	 * */
	@Override
	public ClimateSummary process(ClimateSummary climate) throws Exception {
		// TODO Auto-generated method stub

		Double temp = 0.0;
		if(climate.getHighest_Monthly_Maxi_Temp() == null) climate.setHighest_Monthly_Max_Temp(temp);
		if(climate.getLowest_Monthly_Min_Temp() == null) climate.setLowest_Monthly_Min_Temp(temp);
		if(climate.getMean_Temp() == null) climate.setMean_Temp(temp);
		
		return climate;
	}

}
