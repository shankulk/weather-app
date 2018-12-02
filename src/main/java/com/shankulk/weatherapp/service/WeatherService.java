package com.shankulk.weatherapp.service;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.web.client.RestClientException;

import com.shankulk.weatherapp.dto.WeatherReport;

public interface WeatherService {

	List<WeatherReport> getAverageTemperatureAndPressure(String city) throws RestClientException, URISyntaxException;

}
