/**
 *
 */
package de.tlscrm.dao;

import java.util.List;
import java.util.Optional;

/**
 * The base interface for all Data Access Objects.
 *
 * @author ltegethoff
 *
 */
public interface Dao<T> {

	/**
	 * Get an persisted object by its id.
	 *
	 * @param id the id of the object.
	 * @return An {@link Optional} containing the object if it exists
	 */
	Optional<T> get(long id);

	/**
	 * Gets all available persisted objects.
	 *
	 * @return A {@link List} of all available objects.
	 */
	List<T> getAll();

	/**
	 * Persists an object.
	 *
	 * @param t the object to persist.
	 */
	void save(T t);

	/**
	 * Updates an object with the supplied values.
	 *
	 * @param t the object to update.
	 * @param params
	 */
	void update(T t, String[] params);

	/**
	 * Removes an object from the persistence context.
	 *
	 * @param t the object to remove.
	 */
	void delete(T t);

}
