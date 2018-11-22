package de.tlscrm.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-19T20:58:49.134+0100")
@StaticMetamodel(Weapon.class)
public class Weapon_ {
	public static volatile SingularAttribute<Weapon, Integer> weaponId;
	public static volatile SingularAttribute<Weapon, String> name;
	public static volatile SetAttribute<Weapon, Bout> bouts;
}
