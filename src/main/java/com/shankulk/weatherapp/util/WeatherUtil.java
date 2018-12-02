package com.shankulk.weatherapp.util;

import static com.shankulk.weatherapp.util.WeatherConstants.API_DESCRIPTION;
import static com.shankulk.weatherapp.util.WeatherConstants.API_TERMS_OF_SERVICE;
import static com.shankulk.weatherapp.util.WeatherConstants.CONTACT_EMAIL;
import static com.shankulk.weatherapp.util.WeatherConstants.CONTACT_NAME;
import static com.shankulk.weatherapp.util.WeatherConstants.CONTACT_WEBSITE;
import static com.shankulk.weatherapp.util.WeatherConstants.VERSION;
import static com.shankulk.weatherapp.util.WeatherConstants.WEATHER_FORECAST_APP;
import static java.util.Collections.emptyList;

import org.apache.commons.lang3.StringUtils;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

public class WeatherUtil {
	
	//private constructor of utility class
	private WeatherUtil() {}

	public static ApiInfo getApiInfo() {
		return new ApiInfo(WEATHER_FORECAST_APP,
				API_DESCRIPTION,
				VERSION,
				API_TERMS_OF_SERVICE,
				getContact(),
				StringUtils.EMPTY,
				StringUtils.EMPTY,
				emptyList());
	}

	private static Contact getContact() {
		return new Contact(CONTACT_NAME,
				CONTACT_WEBSITE,
				CONTACT_EMAIL);
	}
}
