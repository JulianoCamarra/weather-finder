package camarra.project.weatherapp.model;

//This class is for the JSON nested property, "sys', which contains one property: the country ISO code for the city
//We use this class if user only inputs a city. we return the most popular city and use that country code to display city

public class Sys {

	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
