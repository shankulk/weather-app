package com.shankulk.weatherapp.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
	
	@JsonProperty("dt")
	private long dateTime;
	private MainData main;

	public long getDateTime() {
		return dateTime;
	}

	public void setDateTime(long dateTime) {
		this.dateTime = dateTime;
	}

	public MainData getMain() {
		return main;
	}

	public void setMain(MainData main) {
		this.main = main;
	}

	public LocalDate getDate() {
		return Instant.ofEpochSecond(dateTime).atZone(ZoneId.of("UTC")).toLocalDate();
	}
	
	public boolean isDaily() {
		LocalTime dailyStartTime = LocalTime.of(6, 00);
		LocalTime dailyFinishTime = LocalTime.of(18, 00);
		LocalDateTime localDt = LocalDateTime.ofInstant(Instant.ofEpochSecond(dateTime), ZoneId.of("UTC"));
		LocalTime lt = LocalTime.of(localDt.getHour(), localDt.getMinute());
		
		return lt.isAfter(dailyStartTime) && lt.isBefore(dailyFinishTime);
	}
	
	public boolean isNightly() {
		return !isDaily();
	}

	@Override
	public String toString() {
		return "Weather [dateTime=" + dateTime + ", date=" + getDate() + ", main=" + main + "]";
	}
	
	
	
}
