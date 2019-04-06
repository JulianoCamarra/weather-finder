package camarra.project.weatherapp.model;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

	private String description;
	private String icon;
	private String fontAwesomeIcon;
	
	@Autowired
	private Environment env;
	/*
	 * the JSON property "weather" pertains to weather conditions rather than
	 * temperature
	 */
     @Autowired
	public Weather() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;

	}

	public String getFontAwesomeIcon() {
		return fontAwesomeIcon;
	}
		@PostConstruct
	public void setFontAwesomeIcon(String fontAwesomeIcon) {
		this.fontAwesomeIcon= fontAwesomeIcon;

	}
		
	public String getFaIcon() {
		
		String jsonIcon= getIcon();
		
		return env.getProperty(jsonIcon);
	}

	@Override
	public String toString() {
		return "Weather [description=" + description + ", icon=" + icon + ", fontAwesomeIcon=" + fontAwesomeIcon + "]";

	}

}
