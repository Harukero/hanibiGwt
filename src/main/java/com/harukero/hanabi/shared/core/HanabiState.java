package com.harukero.hanabi.shared.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.validation.constraints.NotNull;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.harukero.hanabi.shared.utils.SharedUtils;

import gwt.material.design.client.constants.Color;

public class HanabiState implements IsSerializable {
	// private static final Logger logger = Logger.getLogger("HanabiLogger");

	private List<HanabiCard> deck;
	private Map<Color, List<HanabiCard>> cardsByColor;

	private int nbOfPlayers;

	private Map<Integer, List<HanabiCard>> cardsByPlayers;
	private List<HanabiAction> actionsDone = new ArrayList<>();
	private int infoTokens = 8;
	private int lifeTokens = 3;

	public HanabiState() {
		this(5); // default is 5 players
	}

	/**
	 * Defines the current state of a game of Hanabi
	 *
	 * @param nbOfPlayers
	 *            The number of players for the current game
	 * @throws IllegalStateException
	 *             if the number of players isn't between 2 and 5 included
	 */
	public HanabiState(int nbOfPlayers) {
		if (nbOfPlayers < 2 || nbOfPlayers > 5) {
			throw new IllegalStateException("The number of players should be between 2 and 5 included");
		}
		this.nbOfPlayers = nbOfPlayers;
		initCardsByColor();
		initDeck();
		initCardsByPlayers(nbOfPlayers > 3 ? 4 : 5);
	}

	public void addAction(HanabiAction action) {
		actionsDone.add(action);
	}

	public int addInfoToken() {
		if (infoTokens < 8) {
			infoTokens++;
		}
		return infoTokens;
	}

	public HanabiActionStatus discardCard(HanabiCard cardImpacted, int playerId) {
		boolean success = cardsByPlayers.get(playerId).remove(cardImpacted);
		addInfoToken();
		return success ? HanabiActionStatus.SUCCESS : HanabiActionStatus.ERROR;
	}

	/**
	 *
	 * @return the top HanabiCard of the deck. <code>null</code> if the deck is
	 *         empty
	 */
	public HanabiCard drawCard() {
		if (deck.isEmpty()) {
			return null;
		}
		return deck.remove(0);
	}

	public void drawCard(int playerId) {
		HanabiCard card = drawCard();
		if (card != null) {
			card.setOwner(playerId);
			cardsByPlayers.get(playerId).add(card);
		}
	}

	public List<HanabiAction> getActionsDone() {
		List<HanabiAction> reverseActions = new ArrayList<>();
		reverseActions.addAll(actionsDone);
		Collections.reverse(reverseActions);
		return reverseActions;
	}

	public Map<Color, List<HanabiCard>> getCardsByColor() {
		return cardsByColor;
	}

	public Map<Integer, List<HanabiCard>> getCardsByPlayers() {
		return cardsByPlayers;
	}

	public List<HanabiCard> getDeck() {
		return deck;
	}

	public int getInfoLeft() {
		return infoTokens;
	}

	public int getLifeTokens() {
		return lifeTokens;
	}

	public int getNbOfPlayers() {
		return nbOfPlayers;
	}

	public List<HanabiCard> getPlayersHand(int playerId) {
		if (!cardsByPlayers.containsKey(playerId)) {
			throw new IllegalStateException("This player doesn't exist");
		}
		for (HanabiCard hanabiCard : cardsByPlayers.get(playerId)) {
			hanabiCard.setOwner(playerId);
		}
		return cardsByPlayers.get(playerId);
	}

	private void initCardsByColor() {
		cardsByColor = new HashMap<>();
		SharedUtils.HANABI_COLORS.stream().forEach(color -> cardsByColor.put(color, new ArrayList<>()));
	}

	private void initCardsByPlayers(int cardsToDraw) {
		assert deck != null && !deck.isEmpty();
		cardsByPlayers = new HashMap<>();
		IntStream.rangeClosed(1, nbOfPlayers).forEach(playerId -> {
			cardsByPlayers.put(playerId, new ArrayList<>());
			IntStream.rangeClosed(1, cardsToDraw).forEach(cardNumber -> cardsByPlayers.get(playerId).add(drawCard()));
		});
	}

	private void initDeck() {
		deck = new ArrayList<>();
		List<HanabiCard> cards = new ArrayList<>();
		SharedUtils.HANABI_COLORS.stream()
				.forEach(color -> IntStream.rangeClosed(1, 5)
						.forEach(rank -> IntStream.rangeClosed(1, SharedUtils.nbOfCardByRank.get(rank))
								.forEach(itemId -> cards.add(new HanabiCard(color, rank, itemId)))));
		SharedUtils.shuffle(cards);
		deck.addAll(cards);
	}

	public HanabiActionStatus playCard(HanabiCard cardImpacted, int playerId) {
		boolean success = cardsByPlayers.get(playerId).remove(cardImpacted);
		if (!success) {
			return HanabiActionStatus.ERROR;
		}
		success = checkIfCardCanBePlayed(cardImpacted);
		if (!success) {
			return HanabiActionStatus.LIFE_LOST;
		}
		cardsByColor.get(cardImpacted.getColor()).add(cardImpacted);
		cardImpacted.setOwner(0);
		return HanabiActionStatus.SUCCESS;
	}

	private boolean checkIfCardCanBePlayed(@NotNull HanabiCard cardImpacted) {
		List<HanabiCard> cardsForCurrentColor = cardsByColor.get(cardImpacted.getColor());
		if (cardsForCurrentColor.isEmpty()) {
			return cardImpacted.getRank() == 1;
		}
		HanabiCard lastCardPlayed = cardsForCurrentColor.get(cardsForCurrentColor.size() - 1);
		if (lastCardPlayed == null) {
			throw new IllegalStateException("last card played is null, but it shouldn't");
		}
		return cardImpacted.getRank() - lastCardPlayed.getRank() == 1;
	}

	public int removeInfoToken() {
		if (infoTokens > 0) {
			infoTokens--;
		}
		return infoTokens;
	}

	public int removeLifeToken() {
		if (lifeTokens > 0) {
			lifeTokens--;
		}
		return lifeTokens;
	}

	public void setCardsByColor(Map<Color, List<HanabiCard>> cardsByColor) {
		this.cardsByColor = cardsByColor;
	}

	public void setCardsByPlayers(Map<Integer, List<HanabiCard>> cardsByPlayers) {
		this.cardsByPlayers = cardsByPlayers;
	}

	public void setDeck(List<HanabiCard> deck) {
		this.deck = deck;
	}

	public void setInfoLeft(int infoLeft) {
		infoTokens = infoLeft;
	}

	public void setLifeTokens(int lifeTokens) {
		this.lifeTokens = lifeTokens;
	}

}
