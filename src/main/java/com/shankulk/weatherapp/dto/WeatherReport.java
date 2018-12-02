package com.shankulk.weatherapp.dto;

public class WeatherReport {

	private String date;
	private double dailyTemperatureAverage;
	private double nightlyTemperatureAverage;
	private double pressureAverage;

	public WeatherReport(double dailyTemperatureAverage, double nightlyTemperatureAverage, double pressureAverage, String date) {
		this.dailyTemperatureAverage = dailyTemperatureAverage;
		this.nightlyTemperatureAverage = nightlyTemperatureAverage;
		this.pressureAverage = pressureAverage;
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getDailyTemperatureAverage() {
		return dailyTemperatureAverage;
	}

	public void setDailyTemperatureAverage(double dailyTemperatureAverage) {
		this.dailyTemperatureAverage = dailyTemperatureAverage;
	}

	public double getNightlyTemperatureAverage() {
		return nightlyTemperatureAverage;
	}

	public void setNightlyTemperatureAverage(double nightlyTemperatureAverage) {
		this.nightlyTemperatureAverage = nightlyTemperatureAverage;
	}

	public double getPressureAverage() {
		return pressureAverage;
	}

	public void setPressureAverage(double pressureAverage) {
		this.pressureAverage = pressureAverage;
	}

}
