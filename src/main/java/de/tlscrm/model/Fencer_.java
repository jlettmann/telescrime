package de.tlscrm.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-11-19T20:58:49.121+0100")
@StaticMetamodel(Fencer.class)
public class Fencer_ {
	public static volatile SingularAttribute<Fencer, Integer> fencerId;
	public static volatile SingularAttribute<Fencer, String> name;
	public static volatile SetAttribute<Fencer, BoutFencer> boutFencers;
}
