package camarra.project.weatherapp.aspect;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping("/")
public class WeatherExceptionHandler {

	private FileHandler fh;
	private final static Logger myLogger = Logger.getLogger(WeatherExceptionHandler.class.getName());

	public WeatherExceptionHandler() throws IOException {

		fh = new FileHandler("/./resources/mylog.txt", true);
		fh.setFormatter(new SimpleFormatter());
		myLogger.addHandler(fh);
		fh.close();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String logValidationError(MethodArgumentNotValidException exc) {
		BindingResult theResult = exc.getBindingResult();

		List<FieldError> errors = theResult.getFieldErrors();
		for (FieldError error : errors) {
			myLogger.log(Level.INFO, error.toString());
		}
		return "search";
	}

	@ExceptionHandler(Exception.class)
	public String anyOtherError(Exception exc) {
		return "search";
	}
}
