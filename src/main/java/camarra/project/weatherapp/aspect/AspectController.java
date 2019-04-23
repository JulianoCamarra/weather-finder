package camarra.project.weatherapp.aspect;

import java.awt.event.FocusEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.validation.ValidationException;

import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping("weather/")
public class AspectController {

	private FileHandler fh;
	private final static Logger myLogger = Logger.getLogger(AspectController.class.getName());

	public AspectController() throws IOException {

		fh = new FileHandler("/home/juliano/Downloads/springboot/weather-app/src/main/resources/mylog.txt");
		myLogger.addHandler(fh);
		SimpleFormatter sf = new SimpleFormatter();
		fh.setFormatter(sf);
		fh.setLevel(Level.INFO);
		

	}

 @ExceptionHandler(MethodArgumentNotValidException.class)
	public String logValidationError(MethodArgumentNotValidException exc){
		BindingResult theResult = exc.getBindingResult();
		
		System.out.println("inside aspect");
		
		List<FieldError> errors = theResult.getFieldErrors();
		for (FieldError error : errors) {
			myLogger.log(Level.INFO, error.toString());
		}
		 return "search";
	}
 
 @ExceptionHandler(Exception.class)
 public String anyOtherError(Exception exc ) {
	 System.out.println("inside aspect");
	return "search";
	 
	 
 }
}
