package com.boc.weatherapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
 * Purpose: The ClimateSummary model class based on the data structure and necessary to 
 * represent data and object throughout the application. Consists of local variables, getters, setters
 * */
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ClimateSummary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable=false, nullable = false)
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String Station_Name;
	
	private String Province;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date Date;
	
	private Double Mean_Temp;

	private Double Highest_Monthly_Maxi_Temp;
	
	private Double Lowest_Monthly_Min_Temp;

	public String getStation_Name() {
		return Station_Name;
	}
	public void setStation_Name(String station_Name) {
		Station_Name = station_Name;
	}
	public String getProvince() {
		return Province;
	}
	
	public void setProvince(String province) {
		Province = province;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public Double getMean_Temp() {
		return Mean_Temp;
	}
	public void setMean_Temp(Double mean_Temp) {
		Mean_Temp = mean_Temp;
	}
	public Double getHighest_Monthly_Maxi_Temp() {
		return Highest_Monthly_Maxi_Temp;
	}
	public void setHighest_Monthly_Max_Temp(Double highest_Monthly_Max_Temp) {
		Highest_Monthly_Maxi_Temp = highest_Monthly_Max_Temp;
	}
	public Double getLowest_Monthly_Min_Temp() {
		return Lowest_Monthly_Min_Temp;
	}
	public void setLowest_Monthly_Min_Temp(Double lowest_Monthly_Min_Temp) {
		Lowest_Monthly_Min_Temp = lowest_Monthly_Min_Temp;
	}
	@Override
	public String toString() {
		return "ClimateSummary [Station_Name=" + Station_Name + ", Province=" + Province + ", Date=" + Date
				+ ", Mean_Temp=" + Mean_Temp + ", Highest_Monthly_Max_Temp=" + Highest_Monthly_Maxi_Temp
				+ ", Lowest_Monthly_Min_Temp=" + Lowest_Monthly_Min_Temp + "]";
	}

}
