package org.harukero.hanabi.server.rpc;

import org.harukero.hanabi.client.rpc.HanabiStateUpdaterService;
import org.harukero.hanabi.shared.HanabiState;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class HanabiStateUpdaterServiceImpl extends RemoteServiceServlet implements HanabiStateUpdaterService {

	@Override
	public HanabiState updateHanabiState(HanabiState state) {
		return state;
	}

}
