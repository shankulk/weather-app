package com.shankulk.weatherapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shankulk.weatherapp.dto.WeatherReport;
import com.shankulk.weatherapp.service.WeatherService;

@RestController
public class WeatherController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);
	
	@Autowired
	WeatherService weatherService;
	
	@GetMapping(path = "/greetings", produces = {MediaType.TEXT_PLAIN_VALUE})
	public String test() {
		LOGGER.info("Hallo!");
		return "Moin moin!";
	}
	
	@GetMapping(path = "/data", produces = {MediaType.APPLICATION_JSON_VALUE})
	public WeatherReport getWeatherForecast() {
		return weatherService.getAverageTemperatureAndPressure();
	}

}
