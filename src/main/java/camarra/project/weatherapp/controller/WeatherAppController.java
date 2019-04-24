package camarra.project.weatherapp.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import camarra.project.weatherapp.aspect.WeatherExceptionHandler;
import camarra.project.weatherapp.model.City;
import camarra.project.weatherapp.model.StringWrapper;
import camarra.project.weatherapp.service.WeatherService;

@Controller
@RequestMapping("/")
public class WeatherAppController {

	@Autowired
	WeatherService service;

	@Autowired
	WeatherExceptionHandler handler;

	@GetMapping("/")
	public String directToHomepage() {
		return "redirect:search";
	}


	@GetMapping("/search")
	public String displaySearch(Model theModel) {
		theModel.addAttribute("wrapper", new StringWrapper());

		return "search";
	}
	@GetMapping("/current")
	public String getWeather(@Valid @ModelAttribute("wrapper") StringWrapper wrapper, BindingResult theBindingResult,
			Model theModel) {

		String city;
		String country;
		City theCity;

		if (theBindingResult.hasErrors()) {
			System.out.println(theBindingResult.getAllErrors());
			return "search";
		}
		if (wrapper.getCityAndCountry().contains(",")) {
			String[] CityCountry = wrapper.getCityAndCountry().split(",");
			city = CityCountry[0];
			country = CityCountry[1];
			theCity = service.getCityAndCountry(city, country);

		} else {
			city = wrapper.getCityAndCountry();
			theCity = service.getCity(city);
		}

		theModel.addAttribute("city", theCity);
		System.out.println(theCity);
		String test = "cloudy sun with blue skies";
		System.out.println(test.contains("blue"));
		return "search";
	}

	@GetMapping("/error")
	@ExceptionHandler(Exception.class)
	public String displayError(RedirectAttributes redirect) {
		boolean cityNotFound = true;
		redirect.addFlashAttribute("cityNotFound", cityNotFound);
		return "redirect:search";

	}
}