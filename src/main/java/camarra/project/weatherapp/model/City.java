package camarra.project.weatherapp.model;

import java.util.Arrays;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

	//name of city is passed through JSON as a nested object, so we just pass city name as field user entered
	
	@NotNull
	private String cityName;
	
	@NotNull
	@Pattern(regexp="^[a-zA-Z]+$")
	private String countryName;
	
	private String message;
	private int count;
	private List[] list;
	private Main main;
	private String localTime;
	private String timezone;

	public City() {

	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCount() {

		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List[] getList() {
		return list;
	}

	public void setList(List[] list) {
		this.list = list;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public String getLocalTime() {
		return localTime;
	}

	public void setLocalTime(String localTime) {
		this.localTime = localTime;
	}

	
	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	@Override
	public String toString() {
		return "City [country=" + countryName + ", message=" + message + ", count=" + count + ", list="
				+ Arrays.toString(list) + "]";
	}

}
