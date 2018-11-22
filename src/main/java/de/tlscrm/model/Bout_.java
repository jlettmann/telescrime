package de.tlscrm.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-19T20:58:49.078+0100")
@StaticMetamodel(Bout.class)
public class Bout_ {
	public static volatile SingularAttribute<Bout, Integer> boutId;
	public static volatile SingularAttribute<Bout, Weapon> weapon;
	public static volatile SingularAttribute<Bout, Date> boutDate;
	public static volatile SetAttribute<Bout, BoutFencer> boutFencers;
}
