package com.harukero.hanabi.shared.core;

import gwt.material.design.client.constants.Color;

public class HanabiActionBuilder {
	public static HanabiAction createDiscardAction(HanabiCard card, int playerId) {
		HanabiActionBuilder actionBuilder = new HanabiActionBuilder();
		actionBuilder.setActionType(HanabiActionType.DISCARD);
		actionBuilder.setPlayerId(playerId);
		actionBuilder.setCardImpacted(card);
		return actionBuilder.build();
	}

	public static HanabiAction createInfoColorAction(Color color, int playerId) {
		HanabiActionBuilder actionBuilder = new HanabiActionBuilder();
		actionBuilder.setActionType(HanabiActionType.INFORMATION);
		actionBuilder.setPlayerId(playerId);
		actionBuilder.setColorForInfo(color);
		return actionBuilder.build();
	}

	public static HanabiAction createInfoRankAction(int rank, int playerId) {
		HanabiActionBuilder actionBuilder = new HanabiActionBuilder();
		actionBuilder.setActionType(HanabiActionType.INFORMATION);
		actionBuilder.setPlayerId(playerId);
		actionBuilder.setRankForInfo(rank);
		return actionBuilder.build();
	}

	public static HanabiAction createPlayAction(HanabiCard card, int playerId) {
		HanabiActionBuilder actionBuilder = new HanabiActionBuilder();
		actionBuilder.setActionType(HanabiActionType.PLAY);
		actionBuilder.setPlayerId(playerId);
		actionBuilder.setCardImpacted(card);
		return actionBuilder.build();
	}

	private int rankForInfo;
	private int playerId;
	private Color colorForInfo;
	private HanabiCard cardImpacted;

	private HanabiActionType actionType;

	public HanabiActionBuilder() {
	}

	public HanabiAction build() {
		HanabiAction action = new HanabiAction();
		action.setActionType(actionType);
		action.setCardImpacted(cardImpacted);
		action.setColorForInfo(colorForInfo);
		action.setPlayerId(playerId);
		action.setRankForInfo(rankForInfo);
		return action;
	}

	public HanabiActionBuilder setActionType(HanabiActionType actionType) {
		this.actionType = actionType;
		return this;
	}

	public HanabiActionBuilder setCardImpacted(HanabiCard cardImpacted) {
		this.cardImpacted = cardImpacted;
		return this;
	}

	public HanabiActionBuilder setColorForInfo(Color colorForInfo) {
		this.colorForInfo = colorForInfo;
		return this;
	}

	public HanabiActionBuilder setPlayerId(int playerId) {
		this.playerId = playerId;
		return this;
	}

	public HanabiActionBuilder setRankForInfo(int rankForInfo) {
		this.rankForInfo = rankForInfo;
		return this;
	}
}
