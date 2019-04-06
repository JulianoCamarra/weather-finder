package camarra.project.weatherapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import camarra.project.weatherapp.model.City;
import camarra.project.weatherapp.model.Timezone;

@Service
public class TimezoneService {

	@Value("${timezone.api.key}")
	private String key;
	private UriComponents uriComponent;
	private RestTemplate template = new RestTemplate();

	public TimezoneService() {

	}

	public Timezone getTimezone(double lat, double lng) {

		String url = urlCoordParser(lat, lng);
		Timezone theTimezone = template.getForObject(url, Timezone.class);

		return theTimezone;
	}



	public String urlCoordParser(double lat, double lng) {

		uriComponent = UriComponentsBuilder.fromHttpUrl(
				"http://api.timezonedb.com/v2.1/get-time-zone?key={apikey}&format=json&by=position&lat={lat}&lng={lng}")
				.buildAndExpand(key, lat, lng);
		return uriComponent.toString();
	}
	
	public void setLocalTimeOfCity(City city) {

		if (city != null) {
			double lat = city.getList()[0].getCoord().getLat();
			double lng = city.getList()[0].getCoord().getLon();
			Timezone theTimezone = getTimezone(lat, lng);
			System.out.println(theTimezone);

			city.setLocalTime(theTimezone.getFormatted());
			if (theTimezone.getAbbreviation().equals("null")) {
				city.setTimezone(theTimezone.getNextAbbreviation());
			} else {
				city.setTimezone(theTimezone.getAbbreviation());
			}
		}

	}
}
