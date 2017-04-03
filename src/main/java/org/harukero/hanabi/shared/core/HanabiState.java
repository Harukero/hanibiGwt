package org.harukero.hanabi.shared.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import org.harukero.hanabi.shared.utils.SharedUtils;

import com.google.gwt.user.client.rpc.IsSerializable;

import gwt.material.design.client.constants.Color;

public class HanabiState implements IsSerializable {
	private static final Logger logger = Logger.getLogger("HanabiLogger");

	private List<HanabiCard> deck;
	private Map<Color, List<HanabiCard>> cardsByColor;

	private int nbOfPlayers;

	private Map<Integer, List<HanabiCard>> cardsByPlayers;

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

	public HanabiActionStatus discardCard(HanabiCard cardImpacted, int playerId) {
		boolean success = cardsByPlayers.get(playerId).remove(cardImpacted);
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
			cardsByPlayers.get(playerId).add(card);
		}
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

	public int getNbOfPlayers() {
		return nbOfPlayers;
	}

	public List<HanabiCard> getPlayersHand(int playerId) {
		if (!cardsByPlayers.containsKey(playerId)) {
			throw new IllegalStateException("This player doesn't exist");
		}
		List<HanabiCard> cards = new ArrayList<>();
		cards.addAll(cardsByPlayers.get(playerId));
		return cards;
	}

	private void initCardsByColor() {
		cardsByColor = new HashMap<>();
		SharedUtils.HANABI_COLORS.stream().forEach(color -> cardsByColor.put(color, new ArrayList<>()));
	}

	private void initCardsByPlayers(int cardsToDraw) {
		assert (deck != null && !deck.isEmpty());
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
		logger.log(Level.INFO, "trying to remove " + cardImpacted + " is it a success? " + success);
		if (!success) {
			return HanabiActionStatus.ERROR;
		}
		this.cardsByColor.get(cardImpacted.getColor()).add(cardImpacted);
		return HanabiActionStatus.SUCCESS;
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

	public void setNbOfPlayers(int nbOfPlayers) {
		this.nbOfPlayers = nbOfPlayers;
	}

}
