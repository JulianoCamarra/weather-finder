package camarra.project.weatherapp.model;


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
	
	int temperature;
	
	double windSpeed;
	double humidity;
	double longitude;
	double latitude;
	
	String cloudiness;
	String icon;
	String currentTime;
	String timezone;
	
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

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getCloudiness() {
		return cloudiness;
	}

	public void setCloudiness(String cloudiness) {
		this.cloudiness = cloudiness;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	@Override
	public String toString() {
		return "City [cityName=" + cityName + ", countryName=" + countryName + ", temperature=" + temperature
				+ ", windSpeed=" + windSpeed + ", humidity=" + humidity + ", longitude=" + longitude + ", latitude="
				+ latitude + ", cloudiness=" + cloudiness + ", icon=" + icon + ", currentTime=" + currentTime
				+ ", timezone=" + timezone + "]";
	}

}
