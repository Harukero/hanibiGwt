package org.harukero.hanabi.client.views;

import org.harukero.hanabi.client.utils.ViewUtils;

import com.google.gwt.event.dom.client.HasClickHandlers;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardAction;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;
import gwt.material.design.client.ui.MaterialLabel;

public class HanabiCardView extends MaterialCard {

	public static HanabiCardView createCardForColor(Color color, Integer rank, boolean isForViewPlayer) {
		switch (color) {
		case RED:
			return new HanabiCardView(Color.WHITE, Color.RED, ViewUtils.CONSTANTS.color_red(), rank.toString(),
					isForViewPlayer);
		case GREEN:
			return new HanabiCardView(Color.WHITE, Color.GREEN, ViewUtils.CONSTANTS.color_green(), rank.toString(),
					isForViewPlayer);
		case BLUE:
			return new HanabiCardView(Color.WHITE, Color.BLUE, ViewUtils.CONSTANTS.color_blue(), rank.toString(),
					isForViewPlayer);
		case WHITE:
			return new HanabiCardView(Color.BLACK, Color.WHITE, ViewUtils.CONSTANTS.color_white(), rank.toString(),
					isForViewPlayer);
		case YELLOW:
			return new HanabiCardView(Color.BLACK, Color.YELLOW, ViewUtils.CONSTANTS.color_yellow(), rank.toString(),
					isForViewPlayer);
		default:
			return new HanabiCardView(Color.WHITE, Color.BLACK, ViewUtils.CONSTANTS.color_black(),
					"EVERYTHING IS BLACK → THIS IS AN ERROR", isForViewPlayer);
		}

	}

	private Color textColor;
	private Color backgroundColor;
	private String title;
	private String text;
	private MaterialButton discardButton;
	private MaterialButton playButton;
	private boolean isForViewPlayer;
	private MaterialButton rankInfoButton;
	private MaterialButton colorInfoButton;

	private HanabiCardView(Color textColor, Color backgroundColor, String title, String text, boolean isForViewPlayer) {
		this.textColor = textColor;
		this.backgroundColor = backgroundColor;
		this.title = title;
		this.text = text;
		this.isForViewPlayer = isForViewPlayer;
		MaterialCardTitle cardContentTitle = new MaterialCardTitle();
		cardContentTitle.setText(title);
		cardContentTitle.setTextColor(textColor);

		MaterialLabel cardContentLabel = new MaterialLabel(text);
		cardContentLabel.addStyleName(ViewUtils.RESOURCES.style().fontSize14());
		cardContentLabel.setTextColor(textColor);

		MaterialCardContent cardContent = new MaterialCardContent();
		cardContent.add(cardContentTitle);
		cardContent.add(cardContentLabel);
		this.setBackgroundColor(backgroundColor);
		this.add(cardContent);

		MaterialCardAction action = new MaterialCardAction();
		if (isForViewPlayer) {
			playButton = new MaterialButton("PLAY");
			playButton.addStyleName(ViewUtils.RESOURCES.style().cardButton());
			playButton.setTextColor(Color.BLACK);
			playButton.setTextAlign(TextAlign.RIGHT);
			action.add(playButton);

			discardButton = new MaterialButton("DISCARD");
			discardButton.addStyleName(ViewUtils.RESOURCES.style().cardButton());
			discardButton.setTextColor(Color.BLACK);
			discardButton.setTextAlign(TextAlign.LEFT);
			action.add(discardButton);
		} else {
			colorInfoButton = new MaterialButton("COLOR INFO");
			colorInfoButton.addStyleName(ViewUtils.RESOURCES.style().cardButton());
			colorInfoButton.setTextColor(Color.BLACK);
			colorInfoButton.setTextAlign(TextAlign.RIGHT);
			action.add(colorInfoButton);

			rankInfoButton = new MaterialButton("RANK INFO");
			rankInfoButton.addStyleName(ViewUtils.RESOURCES.style().cardButton());
			rankInfoButton.setTextColor(Color.BLACK);
			rankInfoButton.setTextAlign(TextAlign.LEFT);
			action.add(rankInfoButton);
		}
		this.add(action);
	}

	public Color getCardBackgroundColor() {
		return backgroundColor;
	}

	public String getCardText() {
		return text;
	}

	public Color getCardTextColor() {
		return textColor;
	}

	public String getCardTitle() {
		return title;
	}

	public HasClickHandlers getColorInfoButton() {
		return colorInfoButton;
	}

	public HasClickHandlers getDiscardButton() {
		return discardButton;
	}

	public HasClickHandlers getPlayButton() {
		return playButton;
	}

	public HasClickHandlers getRankInfoButton() {
		return rankInfoButton;
	}

	public void setCardBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setCardText(String text) {
		this.text = text;
	}

	public void setCardTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public void setCardTitle(String title) {
		this.title = title;
	}

	public void setDiscardButton(MaterialButton discardButton) {
		this.discardButton = discardButton;
	}

	public void setPlayButton(MaterialButton playButton) {
		this.playButton = playButton;
	}

}
