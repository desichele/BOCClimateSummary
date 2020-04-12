package com.boc.weatherapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.boc.weatherapp.model.ClimateSummary;
import com.boc.weatherapp.repo.ClimateDataRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClimateSummaryDAO {

	@Mock
	private ClimateDataRepo mockedDataRepo;

	@Before
	public void setup() {
		//MockitoAnnotations.initMocks(this);	
	}
	
	@Test 
	public void testFindClimateSummaryByIdNullTest_returnsTrue() {
		int testId = 123;
		when(mockedDataRepo.findById(testId)).thenReturn(null);
		assertFalse(mockedDataRepo.findById(testId)!=null);
		assertTrue(mockedDataRepo.findById(testId)==null);
	}
	
	@Test
	public void testFindClimateSummaryByDateRange_returnsTrue() {
		Date testStartDate = new Date();
		Date testEndDate = new Date();
		when(mockedDataRepo.getAllBetweenDates(testStartDate, testEndDate)).thenReturn(getMockedClimateSummaryForTesting());
		ClimateSummary clmSummary1 = mockedDataRepo.getAllBetweenDates(testStartDate, testEndDate).get(0);
		ClimateSummary clmSummary2 = getMockedClimateSummaryForTesting().get(0);
		assertTrue(clmSummary1.getStation_Name().equals(clmSummary2.getStation_Name()));
		assertTrue(clmSummary1.getProvince().equals(clmSummary2.getProvince()));
		assertTrue(clmSummary1.getHighest_Monthly_Maxi_Temp().equals(clmSummary2.getHighest_Monthly_Maxi_Temp()));
		assertTrue(clmSummary1.getLowest_Monthly_Min_Temp().equals(clmSummary2.getLowest_Monthly_Min_Temp()));
		assertTrue(clmSummary1.getMean_Temp().equals(clmSummary2.getMean_Temp()));
	}
	
	public List<ClimateSummary> getMockedClimateSummaryForTesting(){
		List<ClimateSummary> lstClimateSummary = new ArrayList<ClimateSummary>();
		ClimateSummary climateSummary = new ClimateSummary();
		climateSummary.setDate(new Date());
		climateSummary.setHighest_Monthly_Max_Temp(10.0);
		climateSummary.setLowest_Monthly_Min_Temp(20.0);
		climateSummary.setMean_Temp(25.0);
		climateSummary.setProvince("TestProvince");
		climateSummary.setStation_Name("TestStation");
		lstClimateSummary.add(climateSummary);
		return lstClimateSummary; 
	}
	
}
