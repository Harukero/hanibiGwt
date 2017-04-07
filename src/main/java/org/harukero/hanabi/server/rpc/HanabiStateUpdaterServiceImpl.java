package org.harukero.hanabi.server.rpc;

import org.harukero.hanabi.client.rpc.HanabiStateUpdaterService;
import org.harukero.hanabi.shared.core.HanabiAction;
import org.harukero.hanabi.shared.core.HanabiActionStatus;
import org.harukero.hanabi.shared.core.HanabiActionType;
import org.harukero.hanabi.shared.core.HanabiCard;
import org.harukero.hanabi.shared.core.HanabiState;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import gwt.material.design.client.constants.Color;

public class HanabiStateUpdaterServiceImpl extends RemoteServiceServlet implements HanabiStateUpdaterService {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private void applyDiscard(HanabiAction action, HanabiState state) throws Exception {
		HanabiActionStatus discardCardSuccess = state.discardCard(action.getCardImpacted(), action.getPlayerId());
		if (discardCardSuccess == HanabiActionStatus.ERROR) {
			throw new Exception("Impossible to discard card " + action.getCardImpacted());
		}
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
			} else if (card.getNumber() == rank) {
				card.setColorKnown(true);
			}
		}
	}

	private void applyPlay(HanabiAction action, HanabiState state) throws Exception {
		HanabiActionStatus playCardSuccess = state.playCard(action.getCardImpacted(), action.getPlayerId());
		if (playCardSuccess == HanabiActionStatus.ERROR) {
			throw new Exception("Impossible to play card " + action.getCardImpacted());
		}
		if (playCardSuccess == HanabiActionStatus.LIFE_LOST) {
			state.removeLifeToken();
		}
		state.drawCard(action.getPlayerId());
	}

	@Override
	public HanabiState updateHanabiState(HanabiAction action, HanabiState state) throws Exception {
		if (action.getActionType() == HanabiActionType.DISCARD) {
			applyDiscard(action, state);
		} else if (action.getActionType() == HanabiActionType.PLAY) {
			applyPlay(action, state);
		} else if (action.getActionType() == HanabiActionType.INFORMATION) {
			applyInformation(action, state);
		}
		state.addAction(action);
		return state;
	}

}
