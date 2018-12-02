package com.shankulk.weatherapp.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.shankulk.weatherapp.dto.WeatherReport;
import com.shankulk.weatherapp.model.MainData;
import com.shankulk.weatherapp.model.Weather;
import com.shankulk.weatherapp.model.WeatherForecast;
import com.shankulk.weatherapp.service.WeatherService;
import com.shankulk.weatherapp.util.WeatherUtil;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceImplTest {
	
	
	@Mock
	RestTemplate restTemplate;
	@InjectMocks
	WeatherService weatherService = new WeatherServiceImpl();
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(WeatherServiceImplTest.class);
		WeatherUtil.fakeFeature = true;
	}
	
	//@Test
	public void testResponseHasThreeDaysData() throws RestClientException, URISyntaxException {
		
		WeatherForecast forecast = getDummyWeatherForcast();
		when(restTemplate.getForObject(any(URI.class), any(Class.class))).thenReturn(forecast);
		List<WeatherReport> response = weatherService.getAverageTemperatureAndPressure("London");
		Assert.assertEquals(3, response.size());
		
	}

	
	public WeatherForecast getDummyWeatherForcast() {
		WeatherForecast forecast = new WeatherForecast();
		
		Weather weather1 = new Weather(1543741200, new MainData(12.9, 1003.16));
		Weather weather2 = new Weather(1543752000, new MainData(13.43, 1003.68));
		Weather weather3 = new Weather(1543762800, new MainData(13.92, 1002.65));
		Weather weather4 = new Weather(1543773600, new MainData(13.61, 1002.55));
		Weather weather5 = new Weather(1543924800, new MainData(6.7, 1026.57));
		Weather weather6 = new Weather(1543935600, new MainData(6.73, 1027.05));
		
		Weather weather11 = new Weather(1543946400, new MainData(5.74, 1026.54));
		Weather weather12 = new Weather(1543957200, new MainData(5.91, 1024.6));
		Weather weather13 = new Weather(1543998600, new MainData(5.49, 1020.66));
		Weather weather14 = new Weather(1544108400, new MainData(11.28, 1021.92));
		Weather weather15 = new Weather(1544119200, new MainData(10.17, 1024.14));
		Weather weather16 = new Weather(1544130000, new MainData(7.69, 1025.79));
		
		forecast.setList(Arrays.<Weather> asList(weather1, weather2, weather3, weather4, weather5, weather6, weather11,
				weather12, weather13, weather14, weather15, weather16));
		forecast.setCnt(12);
		return forecast;
	}
	

}
