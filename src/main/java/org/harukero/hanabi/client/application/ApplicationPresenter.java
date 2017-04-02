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

import java.util.List;

import org.harukero.hanabi.client.controllers.HanabiCardController;
import org.harukero.hanabi.client.views.HanabiCardView;
import org.harukero.hanabi.shared.HanabiCard;
import org.harukero.hanabi.shared.HanabiState;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.Proxy;

import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleItem;
import gwt.material.design.client.ui.MaterialRow;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {

	@ProxyStandard
	interface MyProxy extends Proxy<ApplicationPresenter> {
	}

	interface MyView extends View {
		void addCardForWidgetIfPossible(MaterialCollapsibleBody playersBody, MaterialRow playersCardRow,
				HanabiCardView hanabiCardView);

		MaterialCollapsibleItem getCollapsibleZone_3();

		MaterialCollapsibleItem getCollapsibleZone_4();

		MaterialCollapsibleItem getCollapsibleZone_5();

		MaterialRow getHandRow_1();

		MaterialRow getHandRow_2();

		MaterialRow getHandRow_3();

		MaterialRow getHandRow_4();

		MaterialRow getHandRow_5();

		MaterialCollapsibleBody getPlayer_1();

		MaterialCollapsibleBody getPlayer_2();

		MaterialCollapsibleBody getPlayer_3();

		MaterialCollapsibleBody getPlayer_4();

		MaterialCollapsibleBody getPlayer_5();

		void initView(HanabiState state);
	}

	public static final NestedSlot SLOT_MAIN = new NestedSlot();

	private HanabiState model;

	private MyView view;

	@Inject
	ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);
		this.view = view;
		model = new HanabiState(2);

		initView();
	}

	private void drawForPlayer(List<HanabiCard> cardsList, MaterialCollapsibleBody player, MaterialRow hand) {
		cardsList.stream().forEach(card -> {
			HanabiCardView cardView = HanabiCardView.createCardForColor(card.getColor(), card.getNumber());
			HanabiCardController cardController = new HanabiCardController(card, cardView);
			view.addCardForWidgetIfPossible(player, hand, cardView);
		});
	}

	private void initView() {
		int nbOfPlayers = model.getNbOfPlayers();
		drawForPlayer(model.getPlayersHand(1), view.getPlayer_1(), view.getHandRow_1());
		drawForPlayer(model.getPlayersHand(2), view.getPlayer_2(), view.getHandRow_2());

		if (nbOfPlayers > 2) {
			view.getCollapsibleZone_3().setVisible(true);
			drawForPlayer(model.getPlayersHand(3), view.getPlayer_3(), view.getHandRow_3());
		}
		if (nbOfPlayers > 3) {
			view.getCollapsibleZone_4().setVisible(true);
			drawForPlayer(model.getPlayersHand(4), view.getPlayer_4(), view.getHandRow_4());
		}
		if (nbOfPlayers > 4) {
			view.getCollapsibleZone_5().setVisible(true);
			drawForPlayer(model.getPlayersHand(5), view.getPlayer_5(), view.getHandRow_5());
		}
	}
}
