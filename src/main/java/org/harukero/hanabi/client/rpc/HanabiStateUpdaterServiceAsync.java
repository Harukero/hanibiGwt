package org.harukero.hanabi.client.rpc;

import org.harukero.hanabi.shared.core.HanabiAction;
import org.harukero.hanabi.shared.core.HanabiState;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HanabiStateUpdaterServiceAsync {

	void updateHanabiState(HanabiAction action, HanabiState state, AsyncCallback<HanabiState> callback);

}
