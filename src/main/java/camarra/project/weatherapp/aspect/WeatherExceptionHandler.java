package camarra.project.weatherapp.aspect;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice

@RequestMapping("/")
public class WeatherExceptionHandler {

	public WeatherExceptionHandler() {

	}

	@ExceptionHandler(Exception.class)
	public String anyOtherError(Exception exc) {

		return "search";

	}
}
