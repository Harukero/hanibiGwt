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
package org.harukero.hanabi.client.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.harukero.hanabi.client.views.HanabiCardView;
import org.harukero.hanabi.client.views.PlayerZoneView;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialRow;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
	interface Binder extends UiBinder<Widget, ApplicationView> {
	}

	private Map<MaterialCollapsibleBody, MaterialRow> handsByPlayers;
	private Map<MaterialRow, List<Widget>> cardsInHands;

	@UiField
	MaterialPanel panelPlayers;

	@UiField
	MaterialCollapsible playerZones;

	@Inject
	ApplicationView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		handsByPlayers = new HashMap<>();
		cardsInHands = new HashMap<>();
	}

	@Override
	public void addCardForWidgetIfPossible(PlayerZoneView playerZone, HanabiCardView hanabiCardView) {
		MaterialRow materialRow;
		if (!handsByPlayers.containsKey(playerZone.getPlayerBody())) {
			materialRow = playerZone.getPlayerHand();
			handsByPlayers.put(playerZone.getPlayerBody(), materialRow);
			cardsInHands.put(materialRow, new ArrayList<>());
		}
		playerZone.setVisible(true);
		materialRow = handsByPlayers.get(playerZone.getPlayerBody());
		int size = cardsInHands.get(materialRow).size();
		if (size < 5) {

			MaterialCard card = hanabiCardView;

			MaterialColumn column = new MaterialColumn();
			column.setGrid("s2");
			column.add(card);

			// MaterialDnd.draggable(card);

			materialRow.add(column);
			cardsInHands.get(materialRow).add(card);
		}
	}

	@Override
	public void addPlayerZone(PlayerZoneView playerZone) {
		playerZones.add(playerZone);
	}

}
