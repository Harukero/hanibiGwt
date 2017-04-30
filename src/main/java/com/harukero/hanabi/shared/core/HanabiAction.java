package com.harukero.hanabi.shared.core;

import com.google.gwt.user.client.rpc.IsSerializable;

import gwt.material.design.client.constants.Color;

public class HanabiAction implements IsSerializable {

	private int playerId;
	private HanabiActionType actionType;
	private HanabiCard cardImpacted;
	private int rankForInfo;

	private Color colorForInfo;

	HanabiAction() {

	}

	public HanabiActionType getActionType() {
		return actionType;
	}

	public HanabiCard getCardImpacted() {
		return cardImpacted;
	}

	public Color getColorForInfo() {
		return colorForInfo;
	}

	public int getPlayerId() {
		return playerId;
	}

	public int getRankForInfo() {
		return rankForInfo;
	}

	void setActionType(HanabiActionType actionType) {
		this.actionType = actionType;
	}

	void setCardImpacted(HanabiCard cardImpacted) {
		this.cardImpacted = cardImpacted;
	}

	void setColorForInfo(Color colorForInfo) {
		this.colorForInfo = colorForInfo;
	}

	void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	void setRankForInfo(int rankForInfo) {
		this.rankForInfo = rankForInfo;
	}
}
