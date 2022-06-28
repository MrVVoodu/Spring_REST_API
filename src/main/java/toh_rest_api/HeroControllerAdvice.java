package toh_rest_api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HeroControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(HeroNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String heroNotFoundHandler(HeroNotFoundException ex) {
        return ex.getMessage();
    }

    public static class HeroNotFoundException extends RuntimeException {
        HeroNotFoundException(Long id) {
            super("Could not find hero with id: " + id);
        }
    }
}
