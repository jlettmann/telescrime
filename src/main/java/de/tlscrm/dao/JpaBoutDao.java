package de.tlscrm.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import de.tlscrm.model.Bout;

/**
 * Concrete implementation of the {@link Dao} {@code Interface} for the {@link Bout} entity.
 *
 * @author Lukas
 *
 */
public class JpaBoutDao extends JpaDao<Bout> implements BoutDao {

	@Override
	public Optional<Bout> get(final long id) {
		return Optional.ofNullable(entityManager.find(Bout.class, id));
	}

	@Override
	public List<Bout> getAll() {
		TypedQuery<Bout> getAllBouts =
		        entityManager.createQuery("SELECT b FROM Bout b", Bout.class);
		return getAllBouts.getResultList();
	}

	@Override
	public void save(final Bout t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
	}

	@Override
	public void update(final Bout t, final String[] params) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));
	}

	@Override
	public void delete(final Bout t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));
	}

}
