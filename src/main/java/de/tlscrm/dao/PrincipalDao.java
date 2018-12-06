package de.tlscrm.dao;

import de.tlscrm.model.Principal;

public interface PrincipalDao extends Dao<Principal> {

   Principal getByEMail(String email);

}
