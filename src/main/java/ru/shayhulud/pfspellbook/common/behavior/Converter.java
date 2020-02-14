package ru.shayhulud.pfspellbook.common.behavior;

/**
 * Interface for dto-entity converter behavior services.
 * @param <T> entity type
 * @param <R> dto type
 */
public interface Converter<T, R> {

	T fromDTO(R dto);

	R toDTO(T entity);
}
