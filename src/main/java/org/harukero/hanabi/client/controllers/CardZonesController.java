package org.harukero.hanabi.client.controllers;

import org.harukero.hanabi.client.views.CardZonesView;
import org.harukero.hanabi.shared.core.HanabiCard;

public class CardZonesController {
	private CardZonesView view;

	public CardZonesController(CardZonesView view) {
		this.view = view;
	}

	public void addNewCard(HanabiCard card) {
		view.addNewCard(card.getColor(), card.getNumber());
	}

	public void clear() {
		view.reset();
	}

	public CardZonesView getView() {
		return view;
	}
}
