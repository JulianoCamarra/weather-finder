package camarra.project.weatherapp.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import camarra.project.weatherapp.model.City;

@Service
public class WeatherService {

	@Autowired
	private ObjectMapper mapper;
	
	private UriComponents uriComponent;

	private CountryService countryService;
	private TimezoneService timezoneService;

	@Value("${weather.api.key}")
	private String key;

	@Autowired
	public WeatherService(CountryService countryService, TimezoneService timezoneService) {
		this.countryService = countryService;
		this.timezoneService = timezoneService;
	}

	public String getKey() {
		return this.key;
	}

	public List<City> getCity(String city) throws IOException {

		String noCountrySubmitted = "null";
		List<City> cities= new ArrayList<City>();
		URL url = urlCityParser(city, noCountrySubmitted);

		JsonNode root = mapper.readTree(url);

		JsonNode listNode = root.get("list");
		
	    for (JsonNode node: listNode) {
	    	
	    	City theCity= new City();
	    	
	    	theCity.setCityName(node.get("name").asText());
			theCity.setCountryName(node.at("/sys/country").asText());

			theCity.setLatitude(node.at("/coord/lat").asDouble());
			theCity.setLongitude(node.at("/coord/lon").asDouble());

			theCity.setTemperature(node.at("/main/temp").asInt());
			theCity.setWindSpeed(node.at("/wind/speed").asDouble());
			theCity.setHumidity(node.at("/main/humidity").asDouble());

			JsonNode weatherDescriptionNode = node.get("weather");

			for (JsonNode weatherNode : weatherDescriptionNode) {

				theCity.setCloudiness(weatherNode.get("description").asText());
				theCity.setIcon(weatherNode.get("icon").asText());
			}
			
			cities.add(theCity);
	   
		}

		return cities;
		
	}

	public City getCityAndCountry(String city, String countryName) throws IOException {

		String country = countryService.getCountry(countryName);
		URL cityAndCountry = urlCityParser(city, country);

		JsonNode root = mapper.readTree(cityAndCountry);

		JsonNode list = root.get("list");

		City theCity = createCity(list);

		timezoneService.setLocalTimeOfCity(theCity);

		return theCity;

	}

	public URL urlCityParser(String city, String countryCode) throws MalformedURLException {

		uriComponent = UriComponentsBuilder.fromHttpUrl(
				"https://api.openweathermap.org/data/2.5/find?q={city},{countryCode}&units=metric&APPID={apiKey}")
				.buildAndExpand(city, countryCode, key);

		return uriComponent.toUri().toURL();
	}

	private City createCity(JsonNode listNode) {

		City theCity = new City();

		for (JsonNode node : listNode) {

			theCity.setCityName(node.get("name").asText());
			theCity.setCountryName(node.at("/sys/country").asText());

			theCity.setLatitude(node.at("/coord/lat").asDouble());
			theCity.setLongitude(node.at("/coord/lon").asDouble());

			theCity.setTemperature(node.at("/main/temp").asInt());
			theCity.setWindSpeed(node.at("/wind/speed").asDouble());
			theCity.setHumidity(node.at("/main/humidity").asDouble());

			JsonNode weatherDescriptionNode = node.get("weather");

			for (JsonNode weatherNode : weatherDescriptionNode) {

				theCity.setCloudiness(weatherNode.get("description").asText());
				theCity.setIcon(weatherNode.get("icon").asText());
			}
			
		}

		return theCity;

	}

}
