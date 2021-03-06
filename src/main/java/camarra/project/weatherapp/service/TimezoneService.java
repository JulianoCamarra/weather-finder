package camarra.project.weatherapp.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import camarra.project.weatherapp.model.City;
import camarra.project.weatherapp.model.Timezone;

@Service
public class TimezoneService {

	@Value("${timezone.api.key}")
	private String key;
	private UriComponents uriComponent;
	
	@Autowired
	private ObjectMapper mapper;

	public TimezoneService() {

	}

	public Timezone getTimezone(double lat, double lng) throws JsonParseException, 
															JsonMappingException, IOException {


		URL url = urlCoordParser(lat, lng);
		
		Timezone theTimezone =mapper.readValue(url, Timezone.class);

		return theTimezone;
		
	}

	public URL urlCoordParser(double lat, double lng) throws MalformedURLException {

		uriComponent = UriComponentsBuilder.fromHttpUrl("http://api.timezonedb.com/v2.1/get-time-zone?"
				+ "key={apikey}&format=json&by=position&lat={lat}&lng={lng}").buildAndExpand(key, lat, lng);
		
		return uriComponent.toUri().toURL();
		
	}

	public void setLocalTimeOfCity(City city) throws JsonParseException, JsonMappingException, IOException {

		if (city != null) {

			double lat = city.getLatitude();
			double lng = city.getLongitude();

			Timezone theTimezone = this.getTimezone(lat, lng);

			city.setCurrentTime(theTimezone.getFormatted());

			if (theTimezone.getAbbreviation().equals("null")) {

				city.setTimezone(theTimezone.getNextAbbreviation());
				city.setCurrentTime(theTimezone.getFormatted());

			} else {

				city.setTimezone(theTimezone.getAbbreviation());
			}
		}
	}
}
