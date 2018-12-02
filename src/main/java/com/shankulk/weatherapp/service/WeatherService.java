package com.shankulk.weatherapp.service;

import java.net.URISyntaxException;

import org.springframework.web.client.RestClientException;

import com.shankulk.weatherapp.dto.WeatherReport;

public interface WeatherService {

	WeatherReport getAverageTemperatureAndPressure(String city) throws RestClientException, URISyntaxException;

}
