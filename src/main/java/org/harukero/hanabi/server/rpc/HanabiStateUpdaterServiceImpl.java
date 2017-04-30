package org.harukero.hanabi.server.rpc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.harukero.hanabi.client.rpc.HanabiStateUpdaterService;
import org.harukero.hanabi.shared.core.HanabiAction;
import org.harukero.hanabi.shared.core.HanabiActionBuilder;
import org.harukero.hanabi.shared.core.HanabiActionStatus;
import org.harukero.hanabi.shared.core.HanabiActionType;
import org.harukero.hanabi.shared.core.HanabiCard;
import org.harukero.hanabi.shared.core.HanabiState;
import org.harukero.hanabi.shared.utils.SharedUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import gwt.material.design.client.constants.Color;

public class HanabiStateUpdaterServiceImpl extends RemoteServiceServlet implements HanabiStateUpdaterService {
	// private static final Logger logger = Logger.getLogger("HanabiLogger");

	public HanabiStateUpdaterServiceImpl() {
		hasRankOneUnknown = new HashMap<>();
		hasRankFiveUnknown = new HashMap<>();
		nextRankByColor = new HashMap<>();
		cardsWithMostUnknownColors = new HashMap<>();
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, Boolean> hasRankOneUnknown;
	private Map<Integer, Boolean> hasRankFiveUnknown;
	private Map<Color, Integer> nextRankByColor;
	private Map<Integer, Color> cardsWithMostUnknownColors;

	private void applyDiscard(HanabiAction action, HanabiState state) {
		state.discardCard(action.getCardImpacted(), action.getPlayerId());
		state.drawCard(action.getPlayerId());
	}

	private void applyInformation(HanabiAction action, HanabiState state) {
		state.removeInfoToken();
		int playerId = action.getPlayerId();
		Color color = action.getColorForInfo();
		int rank = action.getRankForInfo();
		for (HanabiCard card : state.getPlayersHand(playerId)) {
			if (card.getColor() == color) {
				card.setColorKnown(true);
			} else if (card.getRank() == rank) {
				card.setRankKnown(true);
			}
		}
	}

	private void applyPlay(HanabiAction action, HanabiState state) {
		HanabiActionStatus playCardSuccess = state.playCard(action.getCardImpacted(), action.getPlayerId());
		if (playCardSuccess == HanabiActionStatus.LIFE_LOST) {
			state.removeLifeToken();
		}
		state.drawCard(action.getPlayerId());
	}

	@Override
	public HanabiState updateHanabiState(HanabiAction action, HanabiState state) {
		applyAndSaveAction(action, state);
		allAiTurns(state, action);
		return state;
	}

	private void applyAndSaveAction(HanabiAction action, HanabiState state) {
		if (action.getActionType() == HanabiActionType.DISCARD) {
			applyDiscard(action, state);
		} else if (action.getActionType() == HanabiActionType.PLAY) {
			applyPlay(action, state);
		} else if (action.getActionType() == HanabiActionType.INFORMATION) {
			applyInformation(action, state);
		}
		state.addAction(action);
	}

	private void allAiTurns(HanabiState state, HanabiAction action) {
		SharedUtils.HANABI_COLORS.stream().forEach(color -> {
			List<Integer> numbers = state.getCardsByColor().get(color).stream().map(card -> card.getRank())
					.collect(Collectors.toList());
			int nextRank = numbers.size() + 1;
			nextRankByColor.put(color, nextRank);
		});
		IntStream.rangeClosed(1, state.getNbOfPlayers()).forEach(player -> {
			hasRankOneUnknown.put(player,
					state.getPlayersHand(player).stream().anyMatch(card -> card.getRank() == 1 && !card.isRankKnown()));
			hasRankFiveUnknown.put(player,
					state.getPlayersHand(player).stream().anyMatch(card -> card.getRank() == 5 && !card.isRankKnown()));
			cardsWithMostUnknownColors.put(player,
					state.getPlayersHand(player).stream().collect(Collectors.groupingBy(card -> card.getColor()))
							.entrySet().stream()
							.max((elem1, elem2) -> elem2.getValue().size() - elem1.getValue().size()).get().getKey());
		});
		IntStream.rangeClosed(2, state.getNbOfPlayers()).forEach(playerId -> {
			doStuff(playerId, state, action);
		});
	}

	private static final Random r = new Random();

	private void doStuff(int playerId, HanabiState state, HanabiAction action) {
		HanabiActionBuilder builder = new HanabiActionBuilder();
		for (int player = 1; player <= state.getNbOfPlayers(); player++) {
			if (playerId != player) {
				// give info about 1
				if (hasRankOneUnknown.get(player) && state.getInfoLeft() > 0 && state.getCardsByPlayers().get(player)
						.stream().filter(card -> card.getRank() == 1 && !card.isRankKnown()).count() != 0) {
					builder.setActionType(HanabiActionType.INFORMATION).setPlayerId(player).setRankForInfo(1);
					HanabiAction newAction = builder.build();
					applyAndSaveAction(newAction, state);
					return;
				} // give info about 5
				else if (hasRankFiveUnknown.get(player) && state.getInfoLeft() > 0 && state.getCardsByPlayers()
						.get(player).stream().filter(card -> card.getRank() == 5 && !card.isRankKnown()).count() != 0) {
					builder.setActionType(HanabiActionType.INFORMATION).setPlayerId(player).setRankForInfo(5);
					HanabiAction newAction = builder.build();
					applyAndSaveAction(newAction, state);
					return;
				} else if (r.nextBoolean() && state.getCardsByPlayers().get(player).stream()
						.filter(card -> !card.isColorKnown()).count() != 0) {
					Color selectedColor = state.getCardsByPlayers().get(player).stream()
							.filter(card -> !card.isColorKnown()).map(card -> card.getColor()).findAny().get();
					builder.setActionType(HanabiActionType.INFORMATION).setPlayerId(player)
							.setColorForInfo(selectedColor);
					HanabiAction newAction = builder.build();
					applyAndSaveAction(newAction, state);
					return;
				} else if (r.nextInt() % 3 == 0 && state.getCardsByPlayers().get(player).stream()
						.filter(card -> !card.isRankKnown()).count() != 0) {
					Integer selectedRank = state.getCardsByPlayers().get(player).stream()
							.filter(card -> !card.isRankKnown()).map(card -> card.getRank()).findAny().get();
					builder.setActionType(HanabiActionType.INFORMATION).setPlayerId(player)
							.setRankForInfo(selectedRank);
					HanabiAction newAction = builder.build();
					applyAndSaveAction(newAction, state);
					return;
				}
			}
		}
		// try to play a card
		if (state.getInfoLeft() > 0) {
			for (HanabiCard card : state.getPlayersHand(playerId)) {
				if (card.isRankKnown() && card.isColorKnown()
						&& card.getRank() == nextRankByColor.get(card.getColor())) {
					builder.setActionType(HanabiActionType.PLAY).setPlayerId(playerId).setCardImpacted(card);
					applyAndSaveAction(builder.build(), state);
					return;
				}
			}
		}
		// check if already played card exists
		for (HanabiCard card : state.getPlayersHand(playerId)) {
			if (card.isRankKnown() && card.isColorKnown() && card.getRank() < nextRankByColor.get(card.getColor())) {
				builder.setActionType(HanabiActionType.DISCARD).setPlayerId(playerId).setCardImpacted(card);
				applyAndSaveAction(builder.build(), state);
				return;
			}
		}
		// (state.getPlayersHand(playerId).size()
		Random r = new Random();
		int nextInt = r.nextInt(state.getPlayersHand(playerId).size());
		HanabiCard hanabiCard = state.getPlayersHand(playerId).get(nextInt);
		builder.setActionType(HanabiActionType.DISCARD).setPlayerId(playerId).setCardImpacted(hanabiCard);
		applyAndSaveAction(builder.build(), state);
		// TODO: check card not dangerous.

	}

}
