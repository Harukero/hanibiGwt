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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import org.harukero.hanabi.client.controllers.HanabiCardController;
import org.harukero.hanabi.client.controllers.PlayerHandViewController;
import org.harukero.hanabi.client.views.HanabiCardView;
import org.harukero.hanabi.client.views.PlayerZoneView;
import org.harukero.hanabi.shared.core.HanabiCard;
import org.harukero.hanabi.shared.core.HanabiState;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.Proxy;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy>
		implements IParent {
	@ProxyStandard
	interface MyProxy extends Proxy<ApplicationPresenter> {
	}

	interface MyView extends View {

		void addCardForWidgetIfPossible(PlayerZoneView playerZone, HanabiCardView cardView);

		void addPlayerZone(PlayerZoneView playerZone);

		void resetPlayerZone();

	}

	private static final Logger logger = Logger.getLogger("HanabiLogger");

	public static final NestedSlot SLOT_MAIN = new NestedSlot();

	private HanabiState model;

	private MyView view;

	private Map<Integer, PlayerHandViewController> playerControllerById = new HashMap<>();

	@Inject
	ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);
		this.view = view;
		model = new HanabiState(4);

		initView(model);
	}

	private void drawForPlayer(List<HanabiCard> cardsList, PlayerHandViewController playerController,
			boolean isForViewPlayer) {
		cardsList.stream().forEach(card -> {
			HanabiCardView cardView = HanabiCardView.createCardForColor(card.getColor(), card.getNumber(),
					isForViewPlayer);
			HanabiCardController cardController = new HanabiCardController(this, model, card, cardView);
			PlayerZoneView playerZone = playerController.getView();
			view.addCardForWidgetIfPossible(playerZone, cardView);
		});
	}

	private void initView(HanabiState model) {
		int nbOfPlayers = model.getNbOfPlayers();
		resetPlayerZones(nbOfPlayers);
		drawForPlayer(model.getPlayersHand(1), playerControllerById.get(1), true);
		drawForPlayer(model.getPlayersHand(2), playerControllerById.get(2), false);

		if (nbOfPlayers > 2) {
			drawForPlayer(model.getPlayersHand(3), playerControllerById.get(3), false);
		}
		if (nbOfPlayers > 3) {
			drawForPlayer(model.getPlayersHand(4), playerControllerById.get(4), false);
		}
		if (nbOfPlayers > 4) {
			drawForPlayer(model.getPlayersHand(5), playerControllerById.get(5), false);
		}
	}

	private void resetPlayerZones(int nbOfPlayers) {
		view.resetPlayerZone();
		IntStream.rangeClosed(1, nbOfPlayers).forEach(playerId -> {
			PlayerZoneView playerView = new PlayerZoneView("Player " + playerId);
			PlayerHandViewController playerController = new PlayerHandViewController(playerView, playerId);
			view.addPlayerZone(playerView);
			playerControllerById.put(playerId, playerController);
		});
	}

	@Override
	public void update(HanabiState model) {
		logger.log(Level.INFO, "update view!");
		this.model = model;
		initView(model);
	}
}
