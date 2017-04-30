package com.harukero.hanabi.client.controllers;

import com.harukero.hanabi.client.views.PlayerZoneView;

public class PlayerHandViewController {
	private PlayerZoneView view;
	private int playerId;

	public PlayerHandViewController(PlayerZoneView view, int playerId) {
		this.view = view;
		this.playerId = playerId;
		view.setVisible(false);

	}

	public int getPlayerId() {
		return playerId;
	}

	public PlayerZoneView getView() {
		return view;
	}

}
