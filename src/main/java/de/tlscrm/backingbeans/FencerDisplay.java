package de.tlscrm.backingbeans;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import de.tlscrm.dao.Dao;
import de.tlscrm.model.Fencer;

@ConversationScoped
@Named
public class FencerDisplay implements Serializable {

	@Inject
	private Conversation conversation;

	@Inject
	@Named("jpaFencerDao")
	private transient Dao<Fencer> fencerDao;

	private Fencer fencer;

	public Fencer getFencer() {
		return this.fencer;
	}

	public void newFencer() {
		conversation.begin();
		fencer = new Fencer();
	}

	public String getName() {
		return fencer.getName();
	}

	public void setName(final String name) {
		fencer.setName(name);
	}

	public void saveFencer() {
		fencerDao.save(fencer);
		this.fencer = null;
		FacesMessage message =
		        new FacesMessage(FacesMessage.SEVERITY_INFO, "New Fencer was saved", null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		conversation.end();
	}
}
