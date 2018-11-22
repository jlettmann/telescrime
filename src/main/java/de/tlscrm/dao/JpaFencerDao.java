package de.tlscrm.dao;

import java.util.List;
import java.util.Optional;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import de.tlscrm.model.Fencer;

/**
 * Concrete implementation of the {@link Dao} {@code Interface} for the {@link Fencer} entity.
 *
 * @author ltegethoff
 *
 */
@Named
public class JpaFencerDao extends JpaDao<Fencer> implements FencerDao {

	@Override
	public Optional<Fencer> get(final long id) {
		return Optional.ofNullable(entityManager.find(Fencer.class, id));
	}

	@Override
	public List<Fencer> getAll() {
		TypedQuery<Fencer> getAllFencers =
		        entityManager.createQuery("SELECT f FROM fencer f", Fencer.class);
		return getAllFencers.getResultList();
	}

}
