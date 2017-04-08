package org.harukero.hanabi.client.views;

import org.harukero.hanabi.client.utils.ViewUtils;
import org.harukero.hanabi.shared.core.HanabiAction;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialPanel;

public class HanabiActionView extends MaterialPanel {

	public HanabiActionView(HanabiAction action) {
		MaterialChip chip = createLabelForAction(action);
		chip.addStyleName(ViewUtils.RESOURCES.style().hanabiChip());
		this.add(chip);
	}

	private MaterialChip createLabelForAction(HanabiAction action) {
		MaterialChip chip = new MaterialChip();
		chip.setIconType(IconType.CLOSE);
		switch (action.getActionType()) {
		case DISCARD:
			chip.setText(action.getCardImpacted().getColor() + " " + action.getCardImpacted().getRank());
			chip.setLetter("D");
			chip.setLetterColor(Color.WHITE);
			chip.setLetterBackgroundColor(Color.RED);
			break;
		case PLAY:
			chip.setText(action.getCardImpacted().getColor() + " " + action.getCardImpacted().getRank());
			chip.setLetter("P");
			chip.setLetterColor(Color.WHITE);
			chip.setLetterBackgroundColor(Color.GREEN);
			break;
		case INFORMATION:
			if (action.getRankForInfo() != 0) {
				chip.setText("'Player " + action.getPlayerId() + "' has " + action.getRankForInfo());
			} else {
				chip.setText("'Player " + action.getPlayerId() + "' has " + action.getColorForInfo());
			}
			chip.setLetter("I");
			chip.setLetterColor(Color.WHITE);
			chip.setLetterBackgroundColor(Color.BLUE);
			break;
		default:
			throw new IllegalStateException("Only INFORMATION, PLAY and DISCARD are allowed as action");
		}
		return chip;
	}

}
