package ru.shayhulud.pfspellbook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.shayhulud.pfspellbook.exception.APIException;
import ru.shayhulud.pfspellbook.exception.NotFoundException;

import java.util.Map;

/**
 * Обработчик общих ошибок для всех контроллеров.
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdviser {

	@ExceptionHandler({NotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handle404(APIException e) {
		log.error("Error during process request:", e);
		return e.getErrors();
	}
}
