package com.harukero.hanabi.client.rpc;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.harukero.hanabi.client.views.HanabiModal;

public abstract class HanabiAsyncCallback<X extends IsSerializable> implements AsyncCallback<X> {

	private static final Logger logger = Logger.getLogger("HanabiLogger");

	@Override
	public void onFailure(Throwable caught) {
		HanabiModal.openModal("Error", "Something went wrong. Error caught: " + caught.getMessage());
		logger.log(Level.SEVERE, "Something went wrong when discarding. Error caught: " + caught.getMessage());
	}

}
