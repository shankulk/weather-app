package com.shankulk.weatherapp.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.shankulk.weatherapp.dto.WeatherReport;
import com.shankulk.weatherapp.model.Weather;
import com.shankulk.weatherapp.model.WeatherForecast;
import com.shankulk.weatherapp.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${weatherapp.rest.baseurl}")
	private String url;

	@Value("${weatherapp.api.key}")
	private String appId;

	@Override
	public List<WeatherReport> getAverageTemperatureAndPressure(final String city)
			throws RestClientException, URISyntaxException {

		LOGGER.info("Retrieving weather data from url: {}", url);
		WeatherForecast response = restTemplate.getForObject(getUrl(city), WeatherForecast.class);

		LocalDate today = LocalDate.now(ZoneId.of("UTC"));
		LocalDate upperLimit = today.plusDays(4);

		// Filter date for next three days and group by dates
		Map<LocalDate, List<Weather>> weatherGroupByDate = response.getList().stream()
				//.peek(System.out::println)
				.filter(data -> {
					return data.getDate().isAfter(today) && data.getDate().isBefore(upperLimit);
				})
				//.peek(System.out::println)
				.collect(Collectors.groupingBy(Weather::getDate));

		return getAverages(weatherGroupByDate);
	}

	private List<WeatherReport> getAverages(Map<LocalDate, List<Weather>> weatherGroupByDate) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		List<WeatherReport> averages = new ArrayList<>(weatherGroupByDate.size());

		// Calculate day wise averages
		weatherGroupByDate.entrySet().forEach(data -> {
			double dailyTemperatureAverage = data.getValue().stream().filter(Weather::isDaily).mapToDouble(weather -> {
				return weather.getMain().getTemp();
			}).average().getAsDouble();

			double nightlyTemperatureAverage = data.getValue().stream().filter(Weather::isNightly)
					.mapToDouble(weather -> {
				return weather.getMain().getTemp();
			}).average().getAsDouble();

			double pressureAverage = data.getValue().stream().mapToDouble(weather -> {
				return weather.getMain().getPressure();
			}).average().getAsDouble();

			averages.add(new WeatherReport(dailyTemperatureAverage, nightlyTemperatureAverage, pressureAverage,
					data.getKey().format(format)));
		});

		return averages;
	}

	public URI getUrl(final String city) throws URISyntaxException {
		return new URI(
				String.format(new StringBuilder(url).append("&q=%s").append("&APPID=%s").toString(), city, appId));
	}

}
