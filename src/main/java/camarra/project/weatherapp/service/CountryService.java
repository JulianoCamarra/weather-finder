package camarra.project.weatherapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import camarra.project.weatherapp.model.Country;

@Service
public class CountryService {

	private UriComponents uriComponent;
	private RestTemplate template = new RestTemplate();

	public CountryService() {

	}

	public Country getCountry(String countryName) {

		Country theCountry;

		if (countryName.equals("empty")) {
			theCountry = new Country();
			theCountry.setAlpha2Code("empty");
			return theCountry;
		}

		String url = this.urlCountryParser(countryName);

		// restcountries.eu returns a JSON array if a country's full name is entered as
		// parameter
		// but returns an nested object if a country's ISO code is entered.
		// so we return country[] if entered parameter is full name, and country if
		// entered parameter is iso code

		if (!alreadyIsoCode(countryName) && countryName != "null") {

			Country[] country = template.getForObject(url, Country[].class);

			return theCountry = country[0];

		} else {
			return theCountry = template.getForObject(url, Country.class);
		}
	}

	// Helper method to convert country code URL to a String
	public String urlCountryParser(String country) {

		if (alreadyIsoCode(country)) {
			uriComponent = UriComponentsBuilder
					.fromHttpUrl("https://restcountries.eu/rest/v2/alpha/{country}?fields=name;alpha2Code")
					.buildAndExpand(country);
			return uriComponent.toString();
		} else {
			uriComponent = UriComponentsBuilder
					.fromHttpUrl("https://restcountries.eu/rest/v2/name/{country}?fields=name;alpha2Code")
					.buildAndExpand(country);

			return uriComponent.toString();
		}
	}

	// Check if country passed by user is already an Iso code
	public boolean alreadyIsoCode(String country) {
		return (country.length() < 3);
	}
}
