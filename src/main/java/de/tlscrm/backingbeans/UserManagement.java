package de.tlscrm.backingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import de.tlscrm.dao.FencerDao;
import de.tlscrm.dao.PrincipalDao;
import de.tlscrm.dao.RoleDao;
import de.tlscrm.model.Fencer;
import de.tlscrm.model.Principal;
import de.tlscrm.model.Role;

/**
 * Backing Bean to handle managing(adding, deleting, updating etc.) of users.
 *
 * @author ltegethoff
 *
 */
@Named
@ViewScoped
public class UserManagement implements Serializable {

	@Inject
	@Named("jpaPrincipalDao")
	private transient PrincipalDao principalDao;

	@Inject
	@Named("jpaRoleDao")
	private transient RoleDao roleDao;

	@Inject
	@Named("jpaFencerDao")
	private transient FencerDao fencerDao;

	@Inject
	private transient FacesContext facesContext;

	private final Fencer newFencer = new Fencer();
	private final Principal newPrincipal = new Principal();

	private List<Role> roles = new ArrayList<>();

	private List<SelectItem> roleSelectItems;


	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(final List<Role> roles) {
		this.roles = roles;
	}

	public Principal getNewPricipal() {
		return this.newPrincipal;
	}

	public Fencer getNewFencer() {
		return this.newFencer;
	}

	/**
	 * Constructs a {@link List} of {@link SelectItem}s which contain a {@link Role} as value and the
	 * {@code Role}'s role name as label.
	 *
	 * @return A {@link List} of {@link SelectItem}s which contain a {@link Role} as value and the
	 *         {@code Role}'s role name as label.
	 */
	public List<SelectItem> getRoleSelectItems() {
		if (this.roleSelectItems == null) {
			List<SelectItem> items = new ArrayList<>();
			for (Role dbRole : roleDao.getAll()) {
				items.add(new SelectItem(dbRole, dbRole.getRole()));
			}
			this.roleSelectItems = items;
		}
		return this.roleSelectItems;
	}

	/**
	 * Ensures the uniqueness of a given E-Mail-Address for {@link Principal}s in the Database.
	 *
	 * @param context
	 * @param component
	 * @param value
	 */
	public void validateEMail(final FacesContext context, final UIComponent component,
	      final Object value) {
		String inputEmail = (String) value;
		if (!principalDao.isEMailFree(inputEmail)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
			      "E-Mail already in use", "The E-Mail address is already used by another user."));
		}
	}

	/**
	 * Ensures the uniqueness of a given Name for a {@link Fencer}.
	 *
	 * @param context
	 * @param component
	 * @param value
	 */
	public void validateName(final FacesContext context, final UIComponent component,
	      final Object value) {
		String inputName = (String) value;
		if (!fencerDao.isNameFree(inputName)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
			      "Fencer already exists", "This Fencer already exists."));
		}
	}

	public void saveUser() {
		if (!this.roles.isEmpty()) {
			newPrincipal.setRoles(new HashSet<>(this.roles));
			principalDao.save(newPrincipal);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
			      "Saved new User!", "New User was successfully saved!"));
		}
	}

	public void saveFencer() {
		fencerDao.save(newFencer);
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
		      "Saved new Fencer!", "New Fencer was successfully saved!"));
	}

}
