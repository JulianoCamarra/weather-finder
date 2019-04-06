package camarra.project.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Json property "main" contains the actual temperature property
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
    /*
	 *we take json properties as double values to get exact weather value, round it, then return it as an int
	 * looks nicer on webpage when temperatures and weather are displayed as whole number rather than decimal number
	 */
	int temp;
	double pressure;
	int humidity;
	int temp_min;
	int temp_max;

	public Main() {
		
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		
		this.temp = (int) Math.round(temp);
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public int getHumidity() {
		return (int)humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = (int)Math.round(humidity);
	}

	public int getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(double temp_min) {
		this.temp_min = (int) Math.round(temp_min);
	}

	public int getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(double temp_max) {
		this.temp_max = (int) Math.round(temp_max);
	}

	@Override
	public String toString() {
		return "Main [temp=" + temp + ", pressure=" + pressure + ", humidity=" + humidity + ", temp_min=" + temp_min
				+ ", temp_max=" + temp_max + "]";
	}
	
		
	
}
