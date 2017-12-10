package com.harukero.hanabi.client.application;

import com.harukero.hanabi.client.views.CardZonesView;
import com.harukero.hanabi.client.views.HanabiActionView;
import com.harukero.hanabi.client.views.HanabiCardView;
import com.harukero.hanabi.client.views.LifeAndInfosTokenContainerView;
import com.harukero.hanabi.client.views.PlayerZoneView;

import gwt.material.design.client.ui.MaterialLink;

public interface IApplicationView {
	void addCardForWidgetIfPossible(PlayerZoneView playerZone, HanabiCardView cardView);

	void addNewActionInfo(HanabiActionView action);

	void addPlayerZone(PlayerZoneView playerZone);

	MaterialLink getFive_players_game();

	MaterialLink getFour_players_game();

	LifeAndInfosTokenContainerView getLifeAndInfos();

	MaterialLink getRules();

	MaterialLink getThree_players_game();

	MaterialLink getTwo_players_game();

	void hideAll();

	void resetAside();

	void resetPlayerZone();

	void showView();

	void setCardsZones(CardZonesView zones);
}
