package ru.shayhulud.pfspellbook;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;

import java.util.Collections;

/**
 * Main App.
 */
@SpringBootApplication
public class PfSpellbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(PfSpellbookApplication.class, args);
	}

	/**
	 * Настройка фабрики мапперов jackson.
	 * Java8 даты записываются как unix-timestamp
	 *
	 * @return фабрика Jackson мапперов.
	 */
	@Bean
	public Jackson2ObjectMapperFactoryBean mapperFactoryBean() {
		Jackson2ObjectMapperFactoryBean factory = new Jackson2ObjectMapperFactoryBean();
		factory.setModules(Collections.singletonList(new JavaTimeModule()));
		factory.setFeaturesToDisable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);
		return factory;
	}
}
