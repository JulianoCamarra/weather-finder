package camarra.project.weatherapp.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CountryService {

	private UriComponents uriComponent;
	
	@Autowired
	private ObjectMapper mapper;

	public CountryService() {

	}

	public String getCountry(String countryName) throws IOException {

		if (countryName.equals("empty")) {

			return "empty";

		}

		URL url = this.urlCountryParser(countryName);

		JsonNode root = mapper.readTree(url);
		
	/*
	 * RestCountries returns object if alphacode is inserted, but returns
	 * an array if full country name is inserted. which is why I use root.get(0) for full name of country
	 */
		if (!alreadyIsoCode(countryName) && countryName != "null") {

			String alphaCode = root.get(0).path("alpha2Code").asText();

			return alphaCode;

		} else {
			
			String alphaCode = root.path("alpha2Code").asText();

			return alphaCode;

		}
	}

	
	public URL urlCountryParser(String country) throws MalformedURLException {

		if (alreadyIsoCode(country)) {
					
			uriComponent = UriComponentsBuilder
					.fromHttpUrl("https://restcountries.eu/rest/v2/alpha/{country}?fields=name;alpha2Code")
					.buildAndExpand(country);
			
			return uriComponent.toUri().toURL();
			
		} else {
			
			uriComponent = UriComponentsBuilder
					.fromHttpUrl("https://restcountries.eu/rest/v2/name/{country}?fields=name;alpha2Code")
					.buildAndExpand(country);

			return uriComponent.toUri().toURL();
			
		}
	}

	public boolean alreadyIsoCode(String country) {
		
		return (country.length() < 3);
		
	}
}
