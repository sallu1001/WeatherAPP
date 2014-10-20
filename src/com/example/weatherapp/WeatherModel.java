package com.example.weatherapp;

public class WeatherModel {

	private String date="";
	private String tempMaxc="";
	private String tempMaxF="";
	private String tempMinC="";
	private String tempMinF="";
	private String weatherType="";
	private String weatherTypeImage="";
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTempMaxc() {
		return tempMaxc;
	}
	public void setTempMaxc(String tempMaxc) {
		this.tempMaxc = tempMaxc;
	}
	public String getTempMaxF() {
		return tempMaxF;
	}
	public void setTempMaxF(String tempMaxF) {
		this.tempMaxF = tempMaxF;
	}
	public String getTempMinC() {
		return tempMinC;
	}
	public void setTempMinC(String tempMinC) {
		this.tempMinC = tempMinC;
	}
	public String getTempMinF() {
		return tempMinF;
	}
	public void setTempMinF(String tempMinF) {
		this.tempMinF = tempMinF;
	}
	public String getWeatherType() {
		return weatherType;
	}
	public void setWeatherType(String weatherType) {
		this.weatherType = weatherType;
	}
	public String getWeatherTypeImage() {
		return weatherTypeImage;
	}
	public void setWeatherTypeImage(String weatherTypeImage) {
		this.weatherTypeImage = weatherTypeImage;
	}
	
	
}
