package de.tlscrm.dao;

import java.util.List;
import java.util.Optional;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import de.tlscrm.model.BoutFencer;
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
		return Optional.ofNullable(entityManager.find(Fencer.class, (int) id));
	}

	@Override
	public List<Fencer> getAll() {
		TypedQuery<Fencer> getAllFencers =
		      entityManager.createQuery("SELECT f FROM Fencer f", Fencer.class);
		return getAllFencers.getResultList();
	}

	@Override
	public int getHitsForFencer(final int id) {
		Optional<Fencer> fencerOp = get(id);
		if (fencerOp.isPresent()) {
			Fencer fencer = fencerOp.get();
			int totalHits = 0;
			for (BoutFencer boutFencer : fencer.getBoutFencers()) {
				totalHits += boutFencer.getHits();
			}
			return totalHits;
		}
		throw new IllegalArgumentException("Invalid id: " + id + " for table fencer.");
	}
}
