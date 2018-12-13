package de.tlscrm.backingbeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import de.tlscrm.dao.FencerDao;
import de.tlscrm.model.Fencer;

@Named
@SessionScoped
public class Ranking implements Serializable {

	@Inject
	private transient FencerDao fencerDao;

	private final Map<Fencer, Integer> hitsForFencer = new HashMap<>();

	private List<Fencer> fencers;

	@PostConstruct
	public void postConstruct() {
		this.fencers = fencerDao.getAll();
	}

	public int getHitsForFencer(final Fencer fencer) {
		return this.hitsForFencer.computeIfAbsent(fencer,
		      f -> fencerDao.getHitsForFencer(f.getFencerId()));
	}

	public List<Fencer> getFencers() {
		return fencers;
	}

}
