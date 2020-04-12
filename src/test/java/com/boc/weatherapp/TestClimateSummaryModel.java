package com.boc.weatherapp;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.boc.weatherapp.climateSummaryException.ClimateDetailDateFormatException;
import com.boc.weatherapp.model.ClimateSummary;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClimateSummaryModel {
	
	@Mock
	private ClimateSummary cSummary;
	
	@Before
	public void setup() {
	}
	
	@Test 
	public void testClimateSummary_StationName_ReturnsTrue() {
		String testStationName = "TestStationName";
		when(cSummary.getStation_Name()).thenReturn(testStationName);
		assertTrue(cSummary.getStation_Name().equals(testStationName));
	}
	
	@Test
	public void testClimateSummary_Province_ReturnsTrue() {
		String testProvince = "TestProvince";
		when(cSummary.getProvince()).thenReturn(testProvince);
		assertTrue(cSummary.getProvince().equals(testProvince));
	}
	
	@Test
	public void testClimateSummary_Date_ReturnsTrue() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date testDate;
		try {
			testDate = sdf.parse("2020-04-01");
		} catch (ParseException e) {
			throw new ClimateDetailDateFormatException();
		}
		when(cSummary.getDate()).thenReturn(testDate);
		assertTrue(cSummary.getDate().equals(testDate));
	}
	
	@Test
	public void testClimateSummary_MeanTemp_ReturnsTrue() {
		double testMeanTemp = 0.0;
		when(cSummary.getMean_Temp()).thenReturn(testMeanTemp);
		assertTrue(cSummary.getMean_Temp().equals(testMeanTemp));
	}
	
	@Test
	public void testClimateSummary_HighestMonthlyMaxTemp_ReturnsTrue() {
		double testHighestMonthlyMaxTemp = 11.0;
		when(cSummary.getHighest_Monthly_Maxi_Temp()).thenReturn(testHighestMonthlyMaxTemp);
		assertTrue(cSummary.getHighest_Monthly_Maxi_Temp().equals(testHighestMonthlyMaxTemp));
	}
	
	@Test
	public void testClimateSummary_LowestMonthlyMinTemp_ReturnsTrue() {
		double testLowestMonth = 12.0;
		when(cSummary.getLowest_Monthly_Min_Temp()).thenReturn(testLowestMonth);
		assertTrue(cSummary.getLowest_Monthly_Min_Temp().equals(testLowestMonth));
	}
}
