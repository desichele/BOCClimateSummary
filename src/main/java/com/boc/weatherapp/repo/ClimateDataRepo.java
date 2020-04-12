package com.boc.weatherapp.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boc.weatherapp.model.ClimateSummary;
/*
 * - Data repository based on the JPARepo which provides basic query out of the box.
 * - A custom query is added to handle post request from the user end. 
 * */
public interface ClimateDataRepo extends JpaRepository<ClimateSummary, Integer>{

	@Query(value = "from ClimateSummary t where Date BETWEEN :startDate AND :endDate")
	public List<ClimateSummary> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
}
