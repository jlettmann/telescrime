package de.tlscrm.dao;

import java.util.function.Consumer;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public abstract class JpaDao<T> implements Dao<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	@Inject
	private UserTransaction transaction;

	/**
	 * Used for any actions that require an active {@link EntityTransaction}s. <br>
	 * <br>
	 * Credit to <a href=
	 * "https://www.baeldung.com/java-dao-pattern">https://www.baeldung.com/java-dao-pattern</a> for
	 * the neat idea!
	 *
	 * @param action
	 * @throws Exception
	 */
	protected void executeInsideTransaction(final Consumer<EntityManager> action) {
		try {
			transaction.begin();
			action.accept(entityManager);
			transaction.commit();
		} catch (RuntimeException re) {
			try {
				transaction.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				// TODO: Logging
				e1.printStackTrace();
			}
			throw re;
		} catch (RollbackException | HeuristicMixedException | HeuristicRollbackException
		        | SystemException | NotSupportedException e) {
			// TODO: Logging
			e.printStackTrace();
		}
	}

	@Override
	public void save(final T t) {
		executeInsideTransaction(em -> em.persist(t));
	}

	@Override
	public void update(final T t, final String[] params) {
		executeInsideTransaction(em -> em.merge(t));
	}

	@Override
	public void delete(final T t) {
		executeInsideTransaction(em -> em.remove(t));
	}
}
