package com.harukero.hanabi.client.views;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.harukero.hanabi.client.i18n.HanabiTextConstants;
import com.harukero.hanabi.client.utils.ViewUtils;
import com.harukero.hanabi.shared.utils.SharedUtils;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialRow;

public class CardZonesView extends MaterialRow {

	private Map<Color, CardZoneView> zoneByColor = new HashMap<>();

	public CardZonesView() {
		init();
	}

	private static HanabiTextConstants contants = GWT.create(HanabiTextConstants.class);
	
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
				view.setCardBackgroundColor(Color.RED).setCardTextColor(Color.WHITE).setCardTitleText(contants.color_red())
						.setCardLabelText("Zone for red cards");
				break;
			case GREEN:
				view.setCardBackgroundColor(Color.GREEN).setCardTextColor(Color.WHITE).setCardTitleText(contants.color_green())
						.setCardLabelText("Zone for green cards");
				break;
			case WHITE:
				view.setCardBackgroundColor(Color.WHITE).setCardTextColor(Color.BLACK).setCardTitleText(contants.color_white())
						.setCardLabelText("Zone for white cards");
				break;
			case BLUE:
				view.setCardBackgroundColor(Color.BLUE).setCardTextColor(Color.WHITE).setCardTitleText(contants.color_blue())
						.setCardLabelText("Zone for blue cards");
				break;
			case YELLOW:
				view.setCardBackgroundColor(Color.YELLOW).setCardTextColor(Color.BLACK).setCardTitleText(contants.color_yellow())
						.setCardLabelText("Zone for yellow cards");
				break;
			default:
				throw new IllegalStateException("Error, the color " + color + " isn't allowed in Hanabi");
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
