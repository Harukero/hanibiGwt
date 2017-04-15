package org.harukero.hanabi.client.views;

import java.util.HashMap;
import java.util.Map;

import org.harukero.hanabi.client.utils.ViewUtils;
import org.harukero.hanabi.shared.utils.SharedUtils;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialRow;

public class CardZonesView extends MaterialRow {

	private Map<Color, CardZoneView> zoneByColor = new HashMap<>();

	public CardZonesView() {
		init();
	}

	private void init() {
		MaterialLabel label = new MaterialLabel("Hanabi");
		MaterialColumn col = new MaterialColumn();
		label.addStyleName(ViewUtils.RESOURCES.style().playerName());
		col.add(label);
		this.add(col);
		for (Color color : SharedUtils.HANABI_COLORS) {
			CardZoneView view = new CardZoneView();
			switch (color) {
			case RED:
				view.setCardBackgroundColor(Color.RED).setCardTextColor(Color.WHITE).setCardTitleText("RED")
						.setCardLabelText("Zone for red cards");
				break;
			case GREEN:
				view.setCardBackgroundColor(Color.GREEN).setCardTextColor(Color.WHITE).setCardTitleText("GREEN")
						.setCardLabelText("Zone for green cards");
				break;
			case WHITE:
				view.setCardBackgroundColor(Color.WHITE).setCardTextColor(Color.BLACK).setCardTitleText("WHITE")
						.setCardLabelText("Zone for white cards");
				break;
			case BLUE:
				view.setCardBackgroundColor(Color.BLUE).setCardTextColor(Color.WHITE).setCardTitleText("BLUE")
						.setCardLabelText("Zone for blue cards");
				break;
			case YELLOW:
				view.setCardBackgroundColor(Color.YELLOW).setCardTextColor(Color.BLACK).setCardTitleText("YELLOW")
						.setCardLabelText("Zone for yellow cards");
				break;
			default:
				throw new IllegalStateException("Error, the color " + color + " isn't allowed in Hannabi");
			}
			zoneByColor.put(color, view);
			this.add(view);
		}
	}

	public void addNewCard(Color color, Integer number) {
		zoneByColor.get(color).addNewCardItem(number);
	}

	public void reset() {
		for (Color color : zoneByColor.keySet()) {
			zoneByColor.get(color).clearCollection();
		}
	}

}
