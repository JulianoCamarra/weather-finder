package camarra.project.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Timezone {

	private String countryCode;
	private String abbreviation;
	private String nextAbbreviation;
	private String formatted;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getNextAbbreviation() {
		return nextAbbreviation;
	}

	public void setNextAbbreviation(String nextAbbreviation) {
		this.nextAbbreviation = nextAbbreviation;
	}

	public String getFormatted() {
		return formatted;
	}

	public void setFormatted(String formatted) {
		this.formatted = formatted;
	}

	@Override
	public String toString() {
		return "Timezone [countryCode=" + countryCode + ", nextAbbreviation=" + nextAbbreviation + ", formatted="
				+ formatted + "]";
	}

}
