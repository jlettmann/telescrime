package de.tlscrm.dao;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.xml.bind.DatatypeConverter;
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
   public boolean isEMailFree(final String email) {
      TypedQuery<Long> getCountByEMail = entityManager
            .createNamedQuery(Principal.COUNT_EMAIL, Long.class).setParameter("email", email);
      return getCountByEMail.getSingleResult() < 1L;
   }

   @Override
   public void save(final Principal t) {
      try {
         byte[] hashedPw = MessageDigest.getInstance("SHA-256")
               .digest(t.getPassword().getBytes(Charset.forName("UTF-8")));
         t.setPassword(DatatypeConverter.printBase64Binary(hashedPw));
         super.save(t);
      } catch (NoSuchAlgorithmException e) {
         // TODO Logging
         // shouldn't happen
         e.printStackTrace();
      }
   }
}
