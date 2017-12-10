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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import com.google.inject.Inject;
import com.harukero.hanabi.client.IEventBus;
import com.harukero.hanabi.client.controllers.CardZonesController;
import com.harukero.hanabi.client.controllers.HanabiCardController;
import com.harukero.hanabi.client.controllers.PlayerHandViewController;
import com.harukero.hanabi.client.views.CardZonesView;
import com.harukero.hanabi.client.views.HanabiActionView;
import com.harukero.hanabi.client.views.HanabiCardView;
import com.harukero.hanabi.client.views.HanabiModal;
import com.harukero.hanabi.client.views.PlayerZoneView;
import com.harukero.hanabi.shared.core.HanabiCard;
import com.harukero.hanabi.shared.core.HanabiState;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import gwt.material.design.client.constants.Color;

@Presenter(view = ApplicationView.class)
public class ApplicationPresenter extends BasePresenter<IApplicationView, IEventBus> implements IParent {

	private static final Logger logger = Logger.getLogger("HanabiLogger");

	private HanabiState model;

	private Map<Integer, PlayerHandViewController> playerControllerById = new HashMap<>();

	private CardZonesController zones;

	@Inject
	ApplicationPresenter() {

	}

	@Override
	public void bind() {
		zones = new CardZonesController(new CardZonesView());
		getView().setCardsZones(zones.getView());
		hideView();
		bindMenu();
	}
	
	private void bindMenu() {
		getView().getTwo_players_game().addClickHandler(event -> startNewGame(2));
		getView().getThree_players_game().addClickHandler(event -> notImplementedYet());
		getView().getFour_players_game().addClickHandler(event -> notImplementedYet());
		getView().getFive_players_game().addClickHandler(event -> startNewGame(5));
		getView().getRules().addClickHandler(event -> notImplementedYet());
	}

	private void drawForPlayer(List<HanabiCard> cardsList, PlayerHandViewController playerController,
			boolean isForViewPlayer) {
		cardsList.stream().forEach(card -> {
			HanabiCardView cardView = HanabiCardView.createCardForColor(card.getColor(), card.getRank(),
					isForViewPlayer);
			HanabiCardController cardController = new HanabiCardController(this, model, card, cardView);
			PlayerZoneView playerZone = playerController.getView();
			getView().addCardForWidgetIfPossible(playerZone, cardController.getView());
		});
	}

	private void hideView() {
		getView().hideAll();
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
		getView().resetPlayerZone();
		IntStream.rangeClosed(1, nbOfPlayers).forEach(playerId -> {
			PlayerZoneView playerView = new PlayerZoneView("Player " + playerId);
			PlayerHandViewController playerController = new PlayerHandViewController(playerView, playerId);
			getView().addPlayerZone(playerView);
			playerControllerById.put(playerId, playerController);
		});
	}

	private void startNewGame(int nbPlayers) {
		model = new HanabiState(nbPlayers);
		getView().showView();
		initView(model);
	}

	@Override
	public void update(HanabiState model) {
		// logger.log(Level.INFO, "update view!");
		this.model = model;
		initView(model);
	}

	private void updateLifeAndInfos(HanabiState model) {
		getView().getLifeAndInfos().reset();
		IntStream.rangeClosed(1, model.getLifeTokens()).forEach(life -> getView().getLifeAndInfos().addNewLife());
		IntStream.rangeClosed(1, model.getInfoLeft()).forEach(life -> getView().getLifeAndInfos().addNewInfo());

	}

	private void updateNewsFeed(HanabiState model) {
		getView().resetAside();
		model.getActionsDone().stream().forEach(action -> getView().addNewActionInfo(new HanabiActionView(action)));
	}
}
