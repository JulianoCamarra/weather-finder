package camarra.project.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* 
 * Use this class to map country name and code from http://restcountries.eu
 * Then pass alpha2Code as country parameter in order to get city weather from openweathermap.org
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class Country {
	
	String name;
	String alpha2Code;
	
	public Country() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlpha2Code() {
		return alpha2Code;
	}

	public void setAlpha2Code(String alpha2Code) {
		this.alpha2Code = alpha2Code;
	}
	
}
