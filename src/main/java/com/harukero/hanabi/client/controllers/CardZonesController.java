package com.harukero.hanabi.client.controllers;

import com.harukero.hanabi.client.views.CardZonesView;
import com.harukero.hanabi.shared.core.HanabiCard;

public class CardZonesController {
	private CardZonesView view;

	public CardZonesController(CardZonesView view) {
		this.view = view;
	}

	public void addNewCard(HanabiCard card) {
		view.addNewCard(card.getColor(), card.getRank());
	}

	public void clear() {
		view.reset();
	}

	public CardZonesView getView() {
		return view;
	}
}
