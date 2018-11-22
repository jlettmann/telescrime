package de.tlscrm.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-19T20:58:49.098+0100")
@StaticMetamodel(BoutFencer.class)
public class BoutFencer_ {
	public static volatile SingularAttribute<BoutFencer, BoutFencerId> id;
	public static volatile SingularAttribute<BoutFencer, Bout> bout;
	public static volatile SingularAttribute<BoutFencer, Fencer> fencer;
	public static volatile SingularAttribute<BoutFencer, Short> hits;
}
