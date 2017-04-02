package org.harukero.hanabi.client.rpc;

import org.harukero.hanabi.shared.HanabiState;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HanabiStateUpdaterServiceAsync {

	void updateHanabiState(HanabiState state, AsyncCallback<HanabiState> callback);

}
