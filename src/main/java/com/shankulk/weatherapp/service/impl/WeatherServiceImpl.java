package com.shankulk.weatherapp.service.impl;

import static com.shankulk.weatherapp.util.WeatherConstants.DATE_FORMAT_YYYY_MM_DD;
import static com.shankulk.weatherapp.util.WeatherConstants.DURATION_4_DAYS;
import static com.shankulk.weatherapp.util.WeatherConstants.UTC_TIME_ZONE;

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
import com.shankulk.weatherapp.util.WeatherUtil;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class WeatherServiceImpl implements WeatherService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${weatherapp.rest.baseurl}")
	private String url;

	@Value("${weatherapp.api.key}")
	private String appId;

	public WeatherServiceImpl() {}

	@Override
	public List<WeatherReport> getAverageTemperatureAndPressure(final String city)
			throws RestClientException, URISyntaxException {

		LOGGER.info("Retrieving weather data from url: {}", url);
		WeatherForecast response = restTemplate.getForObject(getUrl(city), WeatherForecast.class);

		LocalDate today = LocalDate.now(ZoneId.of(UTC_TIME_ZONE));
		LocalDate upperLimit = today.plusDays(DURATION_4_DAYS);

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
		DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM_DD);

		List<WeatherReport> averages = new ArrayList<>(weatherGroupByDate.size());

		// Calculate day wise averages
		for(Map.Entry<LocalDate,List<Weather>> data : weatherGroupByDate.entrySet()) {

			double nightlyTemperatureAverage = data.getValue().stream().filter(Weather::isNightly)
					.mapToDouble(weather -> {
				return weather.getMain().getTemp();
			}).average().getAsDouble();


			double pressureAverage = data.getValue().stream().mapToDouble(weather -> {
				return weather.getMain().getPressure();
			}).average().getAsDouble();

			double dailyTemperatureAverage = data.getValue().stream().filter(Weather::isDaily).peek(ele -> System.out.println("Ele:" + ele)).mapToDouble(weather -> {
				return weather.getMain().getTemp();

			}).average().getAsDouble();
			
			averages.add(new WeatherReport(dailyTemperatureAverage, nightlyTemperatureAverage, pressureAverage,
					data.getKey().format(format)));
		}

		return averages;
	}

	
	@Override
	public URI getUrl(final String city) throws URISyntaxException {
		if(WeatherUtil.fakeFeature)
			url = "localhost:8080";
		return new URI(
				String.format(new StringBuilder(url).append("&q=%s").append("&APPID=%s").toString(), city, appId));
	}

}
