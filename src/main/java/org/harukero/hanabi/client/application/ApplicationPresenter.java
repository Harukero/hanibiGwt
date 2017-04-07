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

import org.harukero.hanabi.client.controllers.CardZonesController;
import org.harukero.hanabi.client.controllers.HanabiCardController;
import org.harukero.hanabi.client.controllers.PlayerHandViewController;
import org.harukero.hanabi.client.views.CardZonesView;
import org.harukero.hanabi.client.views.HanabiActionView;
import org.harukero.hanabi.client.views.HanabiCardView;
import org.harukero.hanabi.client.views.HanabiModal;
import org.harukero.hanabi.client.views.LifeAndInfosTokenContainerView;
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

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialLink;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy>
		implements IParent {
	@ProxyStandard
	interface MyProxy extends Proxy<ApplicationPresenter> {
	}

	interface MyView extends View {

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

	private static final Logger logger = Logger.getLogger("HanabiLogger");

	public static final NestedSlot SLOT_MAIN = new NestedSlot();

	private HanabiState model;

	private MyView view;

	private Map<Integer, PlayerHandViewController> playerControllerById = new HashMap<>();

	private CardZonesController zones;

	@Inject
	ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);
		this.view = view;
		zones = new CardZonesController(new CardZonesView());
		this.view.setCardsZones(zones.getView());
		hideView();
		bindMenu();
	}

	private void bindMenu() {
		view.getTwo_players_game().addClickHandler(event -> startNewGame(2));
		view.getThree_players_game().addClickHandler(event -> notImplementedYet());
		view.getFour_players_game().addClickHandler(event -> notImplementedYet());
		view.getFive_players_game().addClickHandler(event -> notImplementedYet());
		view.getRules().addClickHandler(event -> notImplementedYet());
	}

	private void drawForPlayer(List<HanabiCard> cardsList, PlayerHandViewController playerController,
			boolean isForViewPlayer) {
		cardsList.stream().forEach(card -> {
			HanabiCardView cardView = HanabiCardView.createCardForColor(card.getColor(), card.getNumber(),
					isForViewPlayer);
			HanabiCardController cardController = new HanabiCardController(this, model, card, cardView);
			PlayerZoneView playerZone = playerController.getView();
			view.addCardForWidgetIfPossible(playerZone, cardController.getView());
		});
	}

	private void hideView() {
		view.hideAll();
	}

	private void initView(HanabiState model) {
		int nbOfPlayers = model.getNbOfPlayers();
		resetPlayerZones(nbOfPlayers);
		IntStream.rangeClosed(1, nbOfPlayers).forEach(playerId -> drawForPlayer(model.getPlayersHand(playerId),
				playerControllerById.get(playerId), playerId == 1));
		updateNewsFeed(model);
		updateLifeAndInfos(model);
		updateCardsZones(model);
	}

	private void updateCardsZones(HanabiState model) {
		zones.clear();
		Map<Color, List<HanabiCard>> cardsByColor = model.getCardsByColor();
		cardsByColor.forEach((color, cards) -> cards.stream().forEach(card -> {
			logger.log(Level.INFO, "trying to add card " + card + " for color " + color);
			zones.addNewCard(card);
		}));
	}

	private void notImplementedYet() {
		HanabiModal.openModal("Sorry", "Sorry, this button does nothing yet. Come back later!");
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

	private void startNewGame(int nbPlayers) {
		model = new HanabiState(nbPlayers);
		view.showView();
		initView(model);
	}

	@Override
	public void update(HanabiState model) {
		// logger.log(Level.INFO, "update view!");
		this.model = model;
		initView(model);
	}

	private void updateLifeAndInfos(HanabiState model) {
		view.getLifeAndInfos().reset();
		IntStream.rangeClosed(1, model.getLifeTokens()).forEach(life -> view.getLifeAndInfos().addNewLife());
		IntStream.rangeClosed(1, model.getInfoLeft()).forEach(life -> view.getLifeAndInfos().addNewInfo());

	}

	private void updateNewsFeed(HanabiState model) {
		view.resetAside();
		model.getActionsDone().stream().forEach(action -> view.addNewActionInfo(new HanabiActionView(action)));
	}
}
