package de.tlscrm.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import de.tlscrm.model.Weapon;

/**
 * Concrete implementation of the {@link Dao} {@code Interface} for the {@link Weapon} entity.
 *
 * @author Lukas
 *
 */
public class JpaWeaponDao extends JpaDao<Weapon> {

	@Override
	public Optional<Weapon> get(final long id) {
		return Optional.ofNullable(entityManager.find(Weapon.class, id));
	}

	@Override
	public List<Weapon> getAll() {
		TypedQuery<Weapon> getAllWeapons =
		        entityManager.createQuery("SELECT w FROM Weapon w", Weapon.class);
		return getAllWeapons.getResultList();
	}

	@Override
	public void save(final Weapon t) {
		executeInsideTransaction(entityManager -> entityManager.persist(t));
	}

	@Override
	public void update(final Weapon t, final String[] params) {
		executeInsideTransaction(entityManager -> entityManager.merge(t));
	}

	@Override
	public void delete(final Weapon t) {
		executeInsideTransaction(entityManager -> entityManager.remove(t));
	}

}
