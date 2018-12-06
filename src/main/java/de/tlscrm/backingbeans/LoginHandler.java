package de.tlscrm.backingbeans;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import de.tlscrm.dao.PrincipalDao;
import de.tlscrm.model.Principal;

@Named
@RequestScoped
public class LoginHandler {

   private static final String NAV_HOME_REDIRECT = "index.xhtml?faces-redirect=true";

   @Inject
   private PrincipalDao principalDao;

   @Inject
   private FacesContext facesContext;

   private String email;
   private String password;

   private Principal newPrincipal;
   private Principal currentPrincipal;


   public String login() {
      ExternalContext externalContext = facesContext.getExternalContext();
      HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

      if (request.getUserPrincipal() == null) {
         try {
            request.login(email, password);
            currentPrincipal = principalDao.getByEMail(email);
            externalContext.getSessionMap().put("principal", currentPrincipal);
         } catch (ServletException e) {
            facesContext.addMessage(null,
                  new FacesMessage("Login failed", "Username or Password unknown"));
            e.printStackTrace();
            return null;
         }
      }
      return NAV_HOME_REDIRECT;
   }

   public String logout() {
      ExternalContext externalContext = facesContext.getExternalContext();
      HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
      try {
         request.logout();
         externalContext.invalidateSession();
      } catch (ServletException e) {
         // TODO: logging/error handling
         facesContext.addMessage(null, new FacesMessage("Logout Failed!"));
         e.printStackTrace();
         return null;
      }
      // navigate
      return NAV_HOME_REDIRECT;
   }


   public String getEmail() {
      return email;
   }


   public void setEmail(final String email) {
      this.email = email;
   }


   public String getPassword() {
      return password;
   }


   public void setPassword(final String password) {
      this.password = password;
   }
}
