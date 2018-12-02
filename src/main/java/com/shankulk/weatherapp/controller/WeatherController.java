package com.shankulk.weatherapp.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.shankulk.weatherapp.dto.WeatherReport;
import com.shankulk.weatherapp.service.WeatherService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/")
public class WeatherController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);
	
	@Autowired
	WeatherService weatherService;
	
	@ApiOperation(value = "A test api", response = String.class)
	@GetMapping(path = "/greetings", produces = {MediaType.TEXT_PLAIN_VALUE})
	public String test() {
		LOGGER.info("Hallo!");
		return "Moin moin!";
	}
	
	@ApiOperation(value = "Weather report is returned for last three days. Temperature and Pressure are averages for the given day.", response = WeatherReport.class)
	@GetMapping(path = "/data", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody List<WeatherReport> getWeatherForecast(@ApiParam("City for which weather report is fetched.") @RequestParam(value = "city", required = true)final String city) throws RestClientException, URISyntaxException {
		if(null == city)
			throw new IllegalArgumentException("Missing City");
		
		return weatherService.getAverageTemperatureAndPressure(city);
			
	}

}
