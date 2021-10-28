package com.lugano.revobank.services.results;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractResultService {
	
	private List<String> messages = new ArrayList<String>();
	private Boolean successOrFailed;

	public List<String> getMessages() {
		return messages;
	}
	
	public void addMessage(String message) {
		messages.add(message);
	}
	
	public void AddMessages(List<String> messages) {
		this.messages.addAll(messages);
	}
	
	public Boolean getSuccessOrFailed() {
		return successOrFailed;
	}

	public void setSuccessOrFailed(Boolean successOrFailed) {
		this.successOrFailed = successOrFailed;
	}

}
