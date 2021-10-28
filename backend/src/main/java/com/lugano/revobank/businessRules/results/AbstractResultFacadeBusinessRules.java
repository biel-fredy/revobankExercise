package com.lugano.revobank.businessRules.results;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractResultFacadeBusinessRules {

	private List<String> messages = new ArrayList<String>();

	public AbstractResultFacadeBusinessRules() {
	}

	public AbstractResultFacadeBusinessRules(List<String> messages) {
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void addMessages(String messages) {
		this.messages.add(messages);
	}

}
