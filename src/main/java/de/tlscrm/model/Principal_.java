package de.tlscrm.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-12-03T19:55:02.089+0100")
@StaticMetamodel(Principal.class)
public class Principal_ {
	public static volatile SingularAttribute<Principal, Long> id;
	public static volatile SingularAttribute<Principal, String> email;
	public static volatile SingularAttribute<Principal, String> firstname;
	public static volatile SingularAttribute<Principal, String> lastname;
	public static volatile SingularAttribute<Principal, String> password;
	public static volatile SetAttribute<Principal, Role> roles;
}
