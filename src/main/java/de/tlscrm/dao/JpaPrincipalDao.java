package de.tlscrm.dao;

import java.util.List;
import java.util.Optional;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import de.tlscrm.model.Principal;


/**
 * Concrete implementation of the {@link Dao} {@code Interface} for the {@link Principal} entity.
 *
 * @author ltegethoff
 *
 */
@Named
public class JpaPrincipalDao extends JpaDao<Principal> implements PrincipalDao {

   @Override
   public Optional<Principal> get(final long id) {
      return Optional.ofNullable(entityManager.find(Principal.class, id));
   }

   @Override
   public List<Principal> getAll() {
      TypedQuery<Principal> getAllPrincipals =
            entityManager.createQuery("SELECT p FROM Principal p", Principal.class);
      return getAllPrincipals.getResultList();
   }

   @Override
   public Principal getByEMail(final String email) {
      TypedQuery<Principal> getByEMail =
            entityManager.createNamedQuery(Principal.FIND_BY_EMAIL, Principal.class)
                  .setParameter("email", email);
      return getByEMail.getSingleResult();
   }

   @Override
   public void save(final Principal t) {
      // TODO: encrypt Password here
      super.save(t);
   }
}
