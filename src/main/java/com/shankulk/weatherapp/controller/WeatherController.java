package com.shankulk.weatherapp.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

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
	public @ResponseBody List<WeatherReport> getWeatherForecast(@PathParam(value = "city")final String city) throws RestClientException, URISyntaxException {
		return weatherService.getAverageTemperatureAndPressure(city);
	}

}
