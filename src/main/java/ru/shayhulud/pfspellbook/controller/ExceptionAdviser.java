package ru.shayhulud.pfspellbook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.shayhulud.pfspellbook.exception.APIException;
import ru.shayhulud.pfspellbook.exception.NotFoundException;

import java.util.Map;

/**
 * Обработчик общих ошибок для всех контроллеров.
 */
@ControllerAdvice
public class ExceptionAdviser {

	@ExceptionHandler({NotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handle404(APIException e) {
		return e.getErrors();
	}
}
