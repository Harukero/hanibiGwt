package com.harukero.hanabi.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.harukero.hanabi.shared.core.HanabiAction;
import com.harukero.hanabi.shared.core.HanabiState;

public interface HanabiStateUpdaterServiceAsync {

	void updateHanabiState(HanabiAction action, HanabiState state, AsyncCallback<HanabiState> callback);

}
