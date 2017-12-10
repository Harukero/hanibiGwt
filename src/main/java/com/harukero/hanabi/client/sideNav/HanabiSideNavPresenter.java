package com.harukero.hanabi.client.sideNav;

import com.harukero.hanabi.client.IEventBus;
import com.harukero.hanabi.client.views.HanabiModal;
import com.mvp4g.client.presenter.BasePresenter;

public class HanabiSideNavPresenter extends BasePresenter<IHanabiSideNavView, IEventBus> {

	@Override
	public void bind() {
		getView().getTwo_players_game().addClickHandler(event -> getEventBus().startNewGame(2));
		getView().getThree_players_game().addClickHandler(event -> notImplementedYet());
		getView().getFour_players_game().addClickHandler(event -> notImplementedYet());
		getView().getFive_players_game().addClickHandler(event -> getEventBus().startNewGame(5));
		getView().getRules().addClickHandler(event -> notImplementedYet());
	}
	private void notImplementedYet() {
		HanabiModal.openModal("Sorry", "Sorry, this button does nothing yet. Come back later!");
	}

}
