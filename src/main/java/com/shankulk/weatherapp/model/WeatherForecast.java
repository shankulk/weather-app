package com.shankulk.weatherapp.model;

import java.util.List;

public class WeatherForecast {
	
	//private String cod;
	private float message;
	private int cnt;
	List<Weather> list;
	//private String city;
	
	/*public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}*/
	public float getMessage() {
		return message;
	}
	public void setMessage(float message) {
		this.message = message;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public List<Weather> getList() {
		return list;
	}
	public void setList(List<Weather> list) {
		this.list = list;
	}

}
