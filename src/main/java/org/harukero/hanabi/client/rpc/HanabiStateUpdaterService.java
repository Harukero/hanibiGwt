package org.harukero.hanabi.client.rpc;

import org.harukero.hanabi.shared.HanabiState;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("hanabiStateUpdate")
public interface HanabiStateUpdaterService extends RemoteService {

	HanabiState updateHanabiState(HanabiState state);
}
