package com.harukero.hanabi.client.application;

import com.harukero.hanabi.client.views.CardZonesView;
import com.harukero.hanabi.client.views.HanabiActionView;
import com.harukero.hanabi.client.views.HanabiCardView;
import com.harukero.hanabi.client.views.LifeAndInfosTokenContainerView;
import com.harukero.hanabi.client.views.PlayerZoneView;

public interface IApplicationView {
	void addCardForWidgetIfPossible(PlayerZoneView playerZone, HanabiCardView cardView);

	void addNewActionInfo(HanabiActionView action);

	void addPlayerZone(PlayerZoneView playerZone);

	LifeAndInfosTokenContainerView getLifeAndInfos();

	void hideAll();

	void resetAside();

	void resetPlayerZone();

	void showView();

	void setCardsZones(CardZonesView zones);
}
