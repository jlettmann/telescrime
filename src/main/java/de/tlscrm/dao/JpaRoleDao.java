package de.tlscrm.dao;

import java.util.List;
import java.util.Optional;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import de.tlscrm.model.Role;

/**
 * Concrete implementation of the {@link Dao} {@code Interface} for the {@link Role} entity.
 *
 * @author ltegethoff
 *
 */
@Named
public class JpaRoleDao extends JpaDao<Role> implements RoleDao {

	@Override
	public Optional<Role> get(final long id) {
		return Optional.ofNullable(entityManager.find(Role.class, id));
	}

	@Override
	public List<Role> getAll() {
		TypedQuery<Role> getAllRoles = entityManager.createQuery("SELECT r FROM Role r", Role.class);
		return getAllRoles.getResultList();
	}
}
