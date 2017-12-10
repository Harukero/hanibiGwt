package com.harukero.hanabi.client.sideNav;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialLink;

public class HanabiSideNavView extends Composite implements IHanabiSideNavView{

	private static HanabiSideNavViewUiBinder uiBinder = GWT.create(HanabiSideNavViewUiBinder.class);
	
	interface HanabiSideNavViewUiBinder extends UiBinder<Widget, HanabiSideNavView> {
	}

	public HanabiSideNavView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	MaterialLink two_players_game, three_players_game, four_players_game, five_players_game, rules;

	public HanabiSideNavView(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	public MaterialLink getTwo_players_game() {
		return two_players_game;
	}
	
	@Override
	public MaterialLink getThree_players_game() {
		return three_players_game;
	}

	@Override
	public MaterialLink getFour_players_game() {
		return four_players_game;
	}
	
	@Override
	public MaterialLink getFive_players_game() {
		return five_players_game;
	}
	
	@Override
	public MaterialLink getRules() {
		return rules;
	}

}
