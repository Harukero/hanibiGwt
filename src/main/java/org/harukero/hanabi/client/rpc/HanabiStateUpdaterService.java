package org.harukero.hanabi.client.rpc;

import org.harukero.hanabi.shared.core.HanabiAction;
import org.harukero.hanabi.shared.core.HanabiState;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("hanabiStateUpdate")
public interface HanabiStateUpdaterService extends RemoteService {

	HanabiState updateHanabiState(HanabiAction action, HanabiState state) throws Exception;
}
