package camarra.project.weatherapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import camarra.project.weatherapp.model.City;
import camarra.project.weatherapp.model.Country;

@Service
public class WeatherService {

	private RestTemplate template = new RestTemplate();
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

	public City getCity(String city) {

		String noCountrySubmitted = "null";
		String url = urlCityParser(city, noCountrySubmitted);

		City theCity = template.getForObject(url, City.class);
		timezoneService.setLocalTimeOfCity(theCity);

		return theCity;
	}

	public City getCityAndCountry(String city, String countryName) {

		Country country = countryService.getCountry(countryName);
		String cityAndCountry = urlCityParser(city, country.getAlpha2Code());

		City theCity = template.getForObject(cityAndCountry, City.class);
		timezoneService.setLocalTimeOfCity(theCity);

		return theCity;
	}

	public String urlCityParser(String city, String countryCode) {

		uriComponent = UriComponentsBuilder.fromHttpUrl(
				"https://api.openweathermap.org/data/2.5/find?q={city},{countryCode}&units=metric&APPID={apiKey}")
				.buildAndExpand(city, countryCode, key);

		return uriComponent.toString();
	}
}
