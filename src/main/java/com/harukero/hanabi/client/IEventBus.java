package com.harukero.hanabi.client;

import com.harukero.hanabi.client.application.ApplicationPresenter;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.event.EventBus;

@Events(startPresenter = ApplicationPresenter.class)
public interface IEventBus extends EventBus {

	@Event(handlers = ApplicationPresenter.class)
	void startNewGame(int nbOfPlayers);

}
