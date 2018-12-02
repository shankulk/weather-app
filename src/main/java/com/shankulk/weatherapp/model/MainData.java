package com.shankulk.weatherapp.model;

public class MainData {

	private double temp;
	private double pressure;

	public MainData() {}

	public MainData(double temp, double pressure) {
		this.temp = temp;
		this.pressure = pressure;
	}
	
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	
	@Override
	public String toString() {
		return "MainData [temp=" + temp + ", pressure=" + pressure + "]";
	}
	
}
