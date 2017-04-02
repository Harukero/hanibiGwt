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
import org.harukero.hanabi.shared.HanabiState;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleItem;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialRow;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
	interface Binder extends UiBinder<Widget, ApplicationView> {
	}

	private Map<MaterialCollapsibleBody, MaterialRow> handsByPlayers;
	private Map<MaterialRow, List<Widget>> cardsInHands;

	@UiField
	MaterialCollapsibleBody player_2, player_3, player_4, player_5, player_1;

	@UiField
	MaterialCollapsibleItem collapsibleZone_1, collapsibleZone_2, collapsibleZone_3, collapsibleZone_4,
			collapsibleZone_5;

	@UiField
	MaterialPanel panelPlayers;

	// @UiField
	// MaterialCollapsible panelPlayersCollapse;

	@UiField
	MaterialColumn zone1, zone2, zone3, zone4, zone5;

	@UiField
	MaterialRow handRow_1, handRow_2, handRow_3, handRow_4, handRow_5;

	@Inject
	ApplicationView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		handsByPlayers = new HashMap<>();
		cardsInHands = new HashMap<>();
		hidePlayers();
	}

	@Override
	public void addCardForWidgetIfPossible(MaterialCollapsibleBody playersBody, MaterialRow playersCardRow,
			HanabiCardView hanabiCardView) {
		MaterialRow materialRow;
		if (!handsByPlayers.containsKey(playersBody)) {
			materialRow = playersCardRow;
			handsByPlayers.put(playersBody, materialRow);
			cardsInHands.put(materialRow, new ArrayList<>());
		}
		materialRow = handsByPlayers.get(playersBody);
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
	public MaterialCollapsibleItem getCollapsibleZone_3() {
		return collapsibleZone_3;
	}

	@Override
	public MaterialCollapsibleItem getCollapsibleZone_4() {
		return collapsibleZone_4;
	}

	@Override
	public MaterialCollapsibleItem getCollapsibleZone_5() {
		return collapsibleZone_5;
	}

	@Override
	public MaterialRow getHandRow_1() {
		return handRow_1;
	}

	@Override
	public MaterialRow getHandRow_2() {
		return handRow_2;
	}

	@Override
	public MaterialRow getHandRow_3() {
		return handRow_3;
	}

	@Override
	public MaterialRow getHandRow_4() {
		return handRow_4;
	}

	@Override
	public MaterialRow getHandRow_5() {
		return handRow_5;
	}

	@Override
	public MaterialCollapsibleBody getPlayer_1() {
		return player_1;
	}

	@Override
	public MaterialCollapsibleBody getPlayer_2() {
		return player_2;
	}

	@Override
	public MaterialCollapsibleBody getPlayer_3() {
		return player_3;
	}

	@Override
	public MaterialCollapsibleBody getPlayer_4() {
		return player_4;
	}

	@Override
	public MaterialCollapsibleBody getPlayer_5() {
		return player_5;
	}

	private void hidePlayers() {
		collapsibleZone_3.setVisible(false);
		collapsibleZone_4.setVisible(false);
		collapsibleZone_5.setVisible(false);
	}

	@Override
	public void initView(HanabiState model) {

	}
}
