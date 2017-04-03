package org.harukero.hanabi.server.rpc;

import org.harukero.hanabi.client.rpc.HanabiStateUpdaterService;
import org.harukero.hanabi.shared.core.HanabiAction;
import org.harukero.hanabi.shared.core.HanabiState;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class HanabiStateUpdaterServiceImpl extends RemoteServiceServlet implements HanabiStateUpdaterService {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public HanabiState updateHanabiState(HanabiAction action, HanabiState state) {
		return state;
	}

}
