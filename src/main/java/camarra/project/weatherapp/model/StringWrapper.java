package camarra.project.weatherapp.model;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/*
 * We use this class to turn the passed string into an object which can be used as a model
 */

public class StringWrapper {
	@Pattern(regexp = "[a-zA-Z, ]+", message = "not a valid entry")

	@NotBlank
	private String cityAndCountry;

	public String getCityAndCountry() {
		return cityAndCountry;
	}

	public void setCityAndCountry(String cityAndCountry) {
		this.cityAndCountry = cityAndCountry;
	}

}
