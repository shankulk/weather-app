package com.shankulk.weatherapp.service.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.shankulk.weatherapp.dto.WeatherReport;
import com.shankulk.weatherapp.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("weatherapp.rest.baseurl")
	private String url;
	
	@Value("weatherapp.api.key")
	private String appId;
	
	@Override
	public WeatherReport getAverageTemperatureAndPressure(final String city) throws RestClientException, URISyntaxException {
		
		LOGGER.info("Retrieving weather data from url: {}", url);
		Object response = restTemplate.getForObject(getUrl(city), Object.class);
		
		return null;
	}

	public URI getUrl(final String city) throws URISyntaxException {
		return new URI(String.format(new StringBuilder(url).append("&q=%s").append("&APPID=%s").toString(), city, appId));
	}

}
