package de.tlscrm.backingbeans;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
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
import de.tlscrm.model.Role;

/**
 * A Backing Bean to handle login and logout.
 *
 * @author ltegethoff
 *
 */
@Named
@RequestScoped
public class LoginHandler implements Serializable {

	private static final String NAV_HOME_REDIRECT = "/index.xhtml?faces-redirect=true";
	private static final String NAV_LOGIN_REDIRECT = "/dologin.xhtml?faces-redirect=true";


	@Inject
	private transient PrincipalDao principalDao;

	@Inject
	private transient FacesContext facesContext;

	private String email;
	private String password;

	private Principal currentPrincipal;


	@PostConstruct
	public void postConstruct() {
		ExternalContext externalContext = facesContext.getExternalContext();
		currentPrincipal = (Principal) externalContext.getSessionMap().get("principal");
		// in case went to a specific URL
		if (currentPrincipal == null) {
			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
			java.security.Principal principal = request.getUserPrincipal();
			if (principal != null) {
				try {
					currentPrincipal = principalDao.getByEMail(principal.getName());
					// logout whoever and set user to null.
				} catch (Exception ignored) {
					try {
						((HttpServletRequest) externalContext.getRequest()).logout();
					} catch (ServletException alsoIgnored) {
					}
					externalContext.invalidateSession();
					currentPrincipal = null;
				}
			}
		}
	}

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
		return NAV_LOGIN_REDIRECT;
	}

	public boolean currentPrincipalHasRole(final String roleName) {
		Objects.requireNonNull(roleName);
		if (currentPrincipal == null) {
			return false;
		}
		for (Role role : currentPrincipal.getRoles()) {
			if (role.getRole().equalsIgnoreCase(roleName)) {
				return true;
			}
		}
		return false;
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
