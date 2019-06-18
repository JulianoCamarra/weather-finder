package camarra.project.weatherapp.controller;

<<<<<<< HEAD
=======

import java.util.List;

>>>>>>> localapp
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
=======
import org.springframework.web.servlet.ModelAndView;
>>>>>>> localapp
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

<<<<<<< HEAD
	@Autowired
	WeatherExceptionHandler handler;

	@GetMapping("/")
	public String directToHomepage() {
		return "redirect:search";
=======
	// @Autowired
	// WeatherExceptionHandler handler;

	@GetMapping("/")
	public ModelAndView directToHomepage() {
		
		return new ModelAndView("redirect:search");
>>>>>>> localapp
	}

	@GetMapping("/search")
	public ModelAndView displaySearch() {

		ModelAndView modelView= new ModelAndView("search");
		
		modelView.addObject("wrapper", new StringWrapper());
	
		return modelView;

	}

	@GetMapping("/current")
	public ModelAndView getWeather(@Valid @ModelAttribute("wrapper") StringWrapper wrapper, 
								    BindingResult theBindingResult) 			throws Exception {

		ModelAndView resultModelView= new ModelAndView("search");
		String city;
		String country;
		City theCity;
		List<City> cities;

		if (theBindingResult.hasErrors()) {
<<<<<<< HEAD
			return "search";
=======

			return resultModelView;
			
>>>>>>> localapp
		}
		
		if (wrapper.getCityAndCountry().contains(",")) {
			
			String[] CityCountry = wrapper.getCityAndCountry().split(",");

			city = CityCountry[0];
			country = CityCountry[1];

			theCity = service.getCityAndCountry(city, country);

			if (theCity.getCityName().isEmpty()) {

				throw new Exception();
			}

			resultModelView.addObject("city", theCity);

			return resultModelView;

		} else {

			city = wrapper.getCityAndCountry();

			cities = service.getCity(city);

			if (cities.isEmpty()) {
				
				throw new Exception();
			}

			resultModelView.addObject("cities", cities);
			resultModelView.setViewName("city-list");

			return resultModelView;
		}

<<<<<<< HEAD
		theModel.addAttribute("city", theCity);
		return "search";
=======
>>>>>>> localapp
	}

	@GetMapping("/error")
	@ExceptionHandler(Exception.class)
<<<<<<< HEAD
	public String displayError(RedirectAttributes redirect) {
		boolean cityNotFound = true;
		redirect.addFlashAttribute("cityNotFound", cityNotFound);
		return "redirect:search";
=======
	public ModelAndView displayError(RedirectAttributes redirect) {

		boolean cityNotFound = true;
		
		ModelAndView mh= new ModelAndView("redirect:search");
		
		mh.addObject(redirect.addFlashAttribute("cityNotFound", cityNotFound));

		return mh;
>>>>>>> localapp

	}
}