package de.tlscrm.backingbeans;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import de.tlscrm.dao.Dao;
import de.tlscrm.model.Principal;

public class UserManagement implements Serializable {

	@Inject
	@Named("jpaPrincipalDao")
	private Dao<Principal> principalDao;

	private Principal newPrincipal;

	private String email;
	private String password;
	private String firstName;
	private String lastName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
