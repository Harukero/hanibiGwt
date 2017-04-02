package org.harukero.hanabi.client.controllers;

import org.harukero.hanabi.client.views.PlayerZoneView;

public class PlayerHandViewController {
	private PlayerZoneView view;

	public PlayerHandViewController(PlayerZoneView view) {
		this.view = view;
		view.setVisible(false);
	}

	public PlayerZoneView getView() {
		return view;
	}
}
