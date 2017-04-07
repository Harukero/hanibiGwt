package org.harukero.hanabi.client.views;

import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleHeader;
import gwt.material.design.client.ui.MaterialCollapsibleItem;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialRow;

public class PlayerZoneView extends MaterialCollapsibleItem {

	private String playerName;
	private MaterialCollapsibleBody body;
	private MaterialRow row;

	public PlayerZoneView(String playerName) {
		this.playerName = playerName;

		MaterialCollapsibleHeader header = new MaterialCollapsibleHeader();
		MaterialLink materialName = new MaterialLink(playerName);
		header.add(materialName);
		this.add(header);

		body = new MaterialCollapsibleBody();
		row = new MaterialRow();
		body.add(row);
		this.add(body);
	}

	public MaterialCollapsibleBody getPlayerBody() {
		return this.body;
	}

	public MaterialRow getPlayerHand() {
		return this.row;
	}

	public String getPlayerName() {
		return playerName;
	}

}
