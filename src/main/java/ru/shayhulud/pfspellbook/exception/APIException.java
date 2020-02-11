package ru.shayhulud.pfspellbook.exception;

import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Main class of API exception.
 */
public class APIException extends Exception {

	@Getter
	private final Map<String, String> errors;

	public APIException(Map<String, String> errors) {
		super(errors.entrySet().stream()
			.map(_entry -> _entry.getKey() + ":" + _entry.getValue())
			.collect(Collectors.joining(";"))
		);
		this.errors = Collections.unmodifiableMap(errors);
	}

	public APIException(String key, String... values) {
		this(new HashMap<String, String>() {{
			put(key, String.join(",", values));
		}});
	}
}
