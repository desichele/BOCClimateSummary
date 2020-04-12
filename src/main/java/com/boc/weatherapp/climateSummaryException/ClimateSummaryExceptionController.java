package com.boc.weatherapp.climateSummaryException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
/*
 * Controller advice class
 * - this is to handle runtime exception for the application
 * - Controller advice handles different exception type and generate meaningful message 
 * for user to rectify input
 * */
@ControllerAdvice
public class ClimateSummaryExceptionController {
	 
	/*
	 * Climate Detail Exception handler
	 * Purpose : to handle bad post requests where resource page may not exist 404
	 * Input: ClimateDetailNotFoundException of type RuntimeException
	 * Return type : Mapping templates to error
	 * */
	@ExceptionHandler(value = ClimateDetailNotFoundException.class)
	public String exception(ClimateDetailNotFoundException exception, Model model) {
		model.addAttribute("error", "Resource/Climate Summary detail may not exist.");
		model.addAttribute("message", "Please check Id/url and try again. ");
		return "error";
	}
	
	/*
	 * Climate Detail Exception handler
	 * Purpose : to handle bad date format 400
	 * Input: ClimateDetailDateFormatException of type RuntimeException
	 * Return type : Mapping templates to error
	 * */
	@ExceptionHandler(value = ClimateDetailDateFormatException.class)
	public String exception(ClimateDetailDateFormatException exception, Model model) {
		model.addAttribute("error", "Bad Date format. Parsing failed.");
		model.addAttribute("message", "Please check date format and try again. ");
		return "error";
	}
	
	/*
	 * Climate Summary Exception handler
	 * Purpose : to handle internal server error 500
	 * Input: ClimateDetailNotFoundException of type RuntimeException
	 * Return type : Mapping templates to error
	 * */
	@ExceptionHandler(value = ClimateSummaryInternalException.class)
	public String exception(ClimateSummaryInternalException exception, Model model) {
		model.addAttribute("error", "Request processing failed.");
		model.addAttribute("message", "Processing failed. Please try again later. ");
		return "error";
	}
	
	/*
	 * NoHandlerException 
	 * Purpose : default handler
	 * Input: NoHandlerException of type RuntimeException
	 * Return type : Mapping templates to error
	 * */
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public String exception(NoHandlerFoundException exception, Model model) {
		model.addAttribute("error", "Page may not exist. ");
		model.addAttribute("message", "Sorry but the page you looking for may not exist. ");
		return "error";
	}
	
	/*
	 * Climate Summary Exception handler
	 * Purpose : to handle erroneous date where from date needs to be older
	 * Input: ClimateSummaryCustomException of type RuntimeException
	 * Return type : Mapping templates to error
	 * */
	@ExceptionHandler(value = ClimateSummaryCustomException.class)
	public String exception(ClimateSummaryCustomException exception, Model model) {
		model.addAttribute("error", "From date needs to be older than to date.");
		model.addAttribute("message", "Please change the dates and try again.");
		return "error";
	}
}
