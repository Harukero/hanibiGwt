package org.harukero.hanabi.shared;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import org.harukero.hanabi.client.utils.ViewUtils;
import org.harukero.hanabi.shared.utils.SharedUtils;

import gwt.material.design.client.constants.Color;

public class HanabiState {

	private static Map<Integer, Integer> nbOfCardByRank = new HashMap<>();

	static {
		nbOfCardByRank.put(1, 3);
		nbOfCardByRank.put(2, 2);
		nbOfCardByRank.put(3, 2);
		nbOfCardByRank.put(4, 2);
		nbOfCardByRank.put(5, 1);
	}
	private final Logger logger = Logger.getLogger("HanabiLogger");
	private Deque<HanabiCard> deck;
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

	/**
	 *
	 * @return the top HanabiCard of the deck. <code>null</code> if the deck is
	 *         empty
	 */
	public HanabiCard drawCard() {
		if (deck.isEmpty()) {
			return null;
		}
		return deck.pop();
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
		ViewUtils.HANABI_COLORS.stream().forEach(color -> cardsByColor.put(color, new ArrayList<>()));
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
		deck = new ArrayDeque<>();
		List<HanabiCard> cards = new ArrayList<>();
		ViewUtils.HANABI_COLORS.stream().forEach(color -> IntStream.rangeClosed(1, 5)
				.forEach(rank -> IntStream.rangeClosed(1, nbOfCardByRank.get(rank)).forEach(item -> {
					cards.add(new HanabiCard(color, rank));
					logger.log(Level.INFO, "added HanabiCard with color " + color + " and rank " + rank);
				})));
		SharedUtils.shuffle(cards);
		deck.addAll(cards);
	}
}
