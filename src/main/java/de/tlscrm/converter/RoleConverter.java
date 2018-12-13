package de.tlscrm.converter;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;
import de.tlscrm.dao.RoleDao;
import de.tlscrm.model.Role;

/**
 * A custom {@link Converter} for {@link Role}s.
 *
 * @author ltegethoff
 *
 */
@Named
public class RoleConverter implements Converter<Role> {

   @Inject
   @Named("jpaRoleDao")
   private RoleDao roleDao;

   @Override
   public Role getAsObject(final FacesContext context, final UIComponent component,
         final String value) {
      if (value != null) {
         List<Role> roles = roleDao.getAll();
         for (Role role : roles) {
            if (role.getRole().equals(value)) {
               return role;
            }
         }
         throw new ConverterException("Cannot convert " + value + " to a role.");
      }
      return null;
   }

   @Override
   public String getAsString(final FacesContext context, final UIComponent component,
         final Role value) {
      if (value != null) {
         return value.getRole();
      }
      return null;
   }

}
