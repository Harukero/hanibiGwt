package org.harukero.hanabi.client.controllers;

import org.harukero.hanabi.client.views.HanabiCardView;
import org.harukero.hanabi.shared.HanabiCard;

import com.google.gwt.event.dom.client.HasClickHandlers;

import gwt.material.design.client.ui.MaterialToast;

public class HanabiCardController {
	private HanabiCardView view;
	private HanabiCard model;

	public HanabiCardController(HanabiCard model, HanabiCardView view) {
		this.model = model;
		this.view = view;
		bind();
	}

	private void bind() {
		HasClickHandlers discardButton = view.getDiscardButton();
		discardButton.addClickHandler(event -> MaterialToast.fireToast("Card discarded"));
		HasClickHandlers playButton = view.getPlayButton();
		playButton.addClickHandler(event -> MaterialToast.fireToast("Card played"));
	}

}
