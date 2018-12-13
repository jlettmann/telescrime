package de.tlscrm.dao;

import de.tlscrm.model.Fencer;

public interface FencerDao extends Dao<Fencer> {

	int getHitsForFencer(int id);

}
