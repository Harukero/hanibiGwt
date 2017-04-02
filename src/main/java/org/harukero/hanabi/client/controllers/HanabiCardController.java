package org.harukero.hanabi.client.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.harukero.hanabi.client.rpc.HanabiStateUpdaterService;
import org.harukero.hanabi.client.rpc.HanabiStateUpdaterServiceAsync;
import org.harukero.hanabi.client.views.HanabiCardView;
import org.harukero.hanabi.shared.HanabiCard;
import org.harukero.hanabi.shared.HanabiState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;

import gwt.material.design.client.ui.MaterialToast;

public class HanabiCardController {
	private HanabiCardView view;
	private HanabiCard cardModel;
	private final Logger logger = Logger.getLogger("HanabiLogger");

	private HanabiStateUpdaterServiceAsync stockPriceSvc = GWT.create(HanabiStateUpdaterService.class);
	private HanabiState model;

	public HanabiCardController(HanabiState model, HanabiCard cardModel, HanabiCardView view) {
		this.model = model;
		this.cardModel = cardModel;
		this.view = view;
		bind();
	}

	private void bind() {
		HasClickHandlers discardButton = view.getDiscardButton();
		discardButton.addClickHandler(event -> {
			MaterialToast.fireToast("Card discarded");
			AsyncCallback<HanabiState> callback = new AsyncCallback<HanabiState>() {
				@Override
				public void onFailure(Throwable caught) {
					logger.log(Level.SEVERE, "something went wrong. Error caught: " + caught);
				}

				@Override
				public void onSuccess(HanabiState result) {
					logger.log(Level.INFO, "it worked!!!");
				}
			};
			stockPriceSvc.updateHanabiState(model, callback);
		});
		HasClickHandlers playButton = view.getPlayButton();
		playButton.addClickHandler(event -> MaterialToast.fireToast("Card played"));
	}

}
