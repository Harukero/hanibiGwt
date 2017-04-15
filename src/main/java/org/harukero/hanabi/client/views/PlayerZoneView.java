package org.harukero.hanabi.client.views;

import org.harukero.hanabi.client.utils.ViewUtils;

import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialRow;

public class PlayerZoneView extends MaterialPanel {

	private String playerName;
	private MaterialRow row;

	public PlayerZoneView(String playerName) {
		this.playerName = playerName;
		addStyleName(ViewUtils.RESOURCES.style().playerZoneView());
		MaterialLabel name = new MaterialLabel(playerName);
		name.addStyleName(ViewUtils.RESOURCES.style().playerName());
		MaterialColumn col = new MaterialColumn();
		col.add(name);

		row = new MaterialRow();
		row.add(col);
		this.add(row);
	}

	public MaterialRow getPlayerHand() {
		return row;
	}

	public String getPlayerName() {
		return playerName;
	}

}
