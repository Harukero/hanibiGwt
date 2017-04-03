package org.harukero.hanabi.server.rpc;

import org.harukero.hanabi.client.rpc.HanabiStateUpdaterService;
import org.harukero.hanabi.shared.core.HanabiAction;
import org.harukero.hanabi.shared.core.HanabiActionStatus;
import org.harukero.hanabi.shared.core.HanabiActionType;
import org.harukero.hanabi.shared.core.HanabiState;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

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
		// TODO Auto-generated method stub

	}

	private void applyPlay(HanabiAction action, HanabiState state) throws Exception {
		HanabiActionStatus discardCardSuccess = state.playCard(action.getCardImpacted(), action.getPlayerId());
		if (discardCardSuccess == HanabiActionStatus.ERROR) {
			throw new Exception("Impossible to play card " + action.getCardImpacted());
		}
		if (discardCardSuccess == HanabiActionStatus.LIFE_LOST) {

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
		return state;
	}

}
