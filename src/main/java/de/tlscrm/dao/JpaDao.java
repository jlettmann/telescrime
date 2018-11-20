package de.tlscrm.dao;

import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

public abstract class JpaDao<T> implements Dao<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Used for any actions that require an active {@link EntityTransaction}s. <br>
	 * <br>
	 * Credit to <a href=
	 * "https://www.baeldung.com/java-dao-pattern">https://www.baeldung.com/java-dao-pattern</a> for
	 * the neat idea!
	 *
	 * @param action
	 */
	protected void executeInsideTransaction(final Consumer<EntityManager> action) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			action.accept(entityManager);
			transaction.commit();
		} catch (RuntimeException e) {
			transaction.rollback();
			throw e;
		}
	}
}
