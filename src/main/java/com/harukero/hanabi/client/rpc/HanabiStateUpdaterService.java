package com.harukero.hanabi.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.harukero.hanabi.shared.core.HanabiAction;
import com.harukero.hanabi.shared.core.HanabiState;

@RemoteServiceRelativePath("hanabiStateUpdate")
public interface HanabiStateUpdaterService extends RemoteService {

	HanabiState updateHanabiState(HanabiAction action, HanabiState state);
}
