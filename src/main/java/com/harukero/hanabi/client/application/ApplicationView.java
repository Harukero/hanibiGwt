/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.harukero.hanabi.client.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.harukero.hanabi.client.utils.ViewUtils;
import com.harukero.hanabi.client.views.CardZonesView;
import com.harukero.hanabi.client.views.HanabiActionView;
import com.harukero.hanabi.client.views.HanabiCardView;
import com.harukero.hanabi.client.views.LifeAndInfosTokenContainerView;
import com.harukero.hanabi.client.views.PlayerZoneView;

import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialRow;

public class ApplicationView extends Composite implements IApplicationView {
	interface Binder extends UiBinder<Widget, ApplicationView> {
	}
    
	private static final Binder uiBinder = GWT.create(Binder.class);

	private Map<MaterialPanel, MaterialRow> handsByPlayers;
	private Map<MaterialRow, List<Widget>> cardsInHands;

	@UiField
	MaterialPanel playerZones;

	@UiField
	MaterialPanel mainPanel, cardZones;

	@UiField
	MaterialPanel actionFieldAside;

	@UiField
	MaterialPanel lifePointsAndInfos;

	private LifeAndInfosTokenContainerView lifesAndInfos;

	@Inject
	ApplicationView() {
		initWidget(uiBinder.createAndBindUi(this));
		handsByPlayers = new HashMap<>();
		cardsInHands = new HashMap<>();
		lifesAndInfos = new LifeAndInfosTokenContainerView();
		lifePointsAndInfos.add(lifesAndInfos);
	}

	@Override
	public void addCardForWidgetIfPossible(PlayerZoneView playerZone, HanabiCardView hanabiCardView) {
		MaterialRow materialRow;
		if (!handsByPlayers.containsKey(playerZone)) {
			materialRow = playerZone.getPlayerHand();
			handsByPlayers.put(playerZone, materialRow);
			cardsInHands.put(materialRow, new ArrayList<>());
		}
		playerZone.setVisible(true);
		materialRow = handsByPlayers.get(playerZone);
		MaterialColumn column = new MaterialColumn();
		column.addStyleName(ViewUtils.RESOURCES.style().hanabiCard());
		column.add(hanabiCardView);

		materialRow.add(column);
		cardsInHands.get(materialRow).add(hanabiCardView);

	}

	@Override
	public void addNewActionInfo(HanabiActionView action) {
		actionFieldAside.add(action);
	}

	@Override
	public void addPlayerZone(PlayerZoneView playerZone) {
		playerZones.add(playerZone);
	}

	@Override
	public void setCardsZones(CardZonesView zones) {
		cardZones.add(zones);
	}

	@Override
	public LifeAndInfosTokenContainerView getLifeAndInfos() {
		return lifesAndInfos;
	}


	@Override
	public void hideAll() {
		mainPanel.setVisible(false);
		actionFieldAside.setVisible(false);
	}

	@Override
	public void resetAside() {
		actionFieldAside.clear();
	}

	@Override
	public void resetPlayerZone() {
		playerZones.clear();
		handsByPlayers.clear();
		cardsInHands.clear();
	}

	@Override
	public void showView() {
		mainPanel.setVisible(true);
		actionFieldAside.setVisible(true);
	}
}
