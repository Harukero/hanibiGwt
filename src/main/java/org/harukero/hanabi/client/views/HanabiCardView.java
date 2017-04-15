package org.harukero.hanabi.client.views;

import org.harukero.hanabi.client.utils.ViewUtils;

import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialCollectionItem;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;

public class HanabiCardView extends MaterialCard implements HasAllMouseHandlers {

	private static final Color unknownInfoColor = Color.BLACK;

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
	private String cardColor;
	private String cardRank;
	private MaterialLink discardButton;
	private MaterialLink playButton;
	private boolean isForViewPlayer;
	private MaterialLink rankInfoButton;
	private MaterialLink colorInfoButton;
	private boolean cardColorDisplayed;
	private boolean cardRankDisplayed;
	private MaterialCardTitle cardColorContainer;
	private MaterialLabel cardRankContainer;
	private HanabiPopupPanel cardMenu;

	private HanabiCardView(Color textColor, Color backgroundColor, String cardColor, String cardRank,
			boolean isForViewPlayer) {

		this.textColor = textColor;
		this.backgroundColor = backgroundColor;
		this.cardColor = cardColor;
		this.cardRank = cardRank;
		this.isForViewPlayer = isForViewPlayer;
		cardColorContainer = new MaterialCardTitle();
		cardColorContainer.setText(cardColor);
		cardRankContainer = new MaterialLabel(cardRank);
		cardRankContainer.addStyleName(ViewUtils.RESOURCES.style().fontSize14());

		MaterialCardContent cardContent = new MaterialCardContent();
		cardContent.add(cardColorContainer);
		cardContent.add(cardRankContainer);
		this.add(cardContent);
		buildActionMenu(isForViewPlayer);
		setMenuClickHandler();

		cardColorContainer.setTextColor(isForViewPlayer ? unknownInfoColor : textColor);
		cardRankContainer.setTextColor(isForViewPlayer ? unknownInfoColor : textColor);
		setBackgroundColor(isForViewPlayer ? unknownInfoColor : backgroundColor);

		// this.add(action);
	}

	private void setMenuClickHandler() {
		addClickHandler(event -> {
			cardMenu.setPopupPositionAndShow((offsetWidth, offsetHeight) -> {
				int left = event.getClientX();
				int top = event.getClientY();
				cardMenu.setPopupPosition(left, top);
			});
		});
	}

	private void buildActionMenu(boolean isForViewPlayer) {
		MaterialCollection action = new MaterialCollection();

		MaterialCollectionItem item;
		if (isForViewPlayer) {
			item = new MaterialCollectionItem();
			playButton = new MaterialLink("PLAY");
			playButton.addStyleName(ViewUtils.RESOURCES.style().cardButton());
			playButton.setTextColor(Color.WHITE);
			item.setBackgroundColor(Color.GREEN);
			item.setTextAlign(TextAlign.CENTER);
			item.add(playButton);
			action.add(item);

			item = new MaterialCollectionItem();
			discardButton = new MaterialLink("DISCARD");
			discardButton.addStyleName(ViewUtils.RESOURCES.style().cardButton());
			discardButton.setTextColor(Color.WHITE);
			item.setBackgroundColor(Color.RED);
			item.setTextAlign(TextAlign.CENTER);
			item.add(discardButton);
			action.add(item);
		} else {
			item = new MaterialCollectionItem();
			colorInfoButton = new MaterialLink("COLOR INFO");
			colorInfoButton.addStyleName(ViewUtils.RESOURCES.style().cardButton());
			colorInfoButton.setTextColor(Color.WHITE);
			item.setBackgroundColor(Color.BLUE);
			item.setTextAlign(TextAlign.CENTER);
			item.add(colorInfoButton);
			action.add(item);

			item = new MaterialCollectionItem();
			rankInfoButton = new MaterialLink("RANK INFO");
			rankInfoButton.addStyleName(ViewUtils.RESOURCES.style().cardButton());
			rankInfoButton.setTextColor(Color.BLACK);
			item.setBackgroundColor(Color.BLUE);
			item.setTextAlign(TextAlign.CENTER);
			item.add(rankInfoButton);
			action.add(item);
		}
		cardMenu = new HanabiPopupPanel(action);
	}

	public Color getCardBackgroundColor() {
		return backgroundColor;
	}

	public String getCardText() {
		return cardRank;
	}

	public Color getCardTextColor() {
		return textColor;
	}

	public String getCardTitle() {
		return cardColor;
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

	public boolean isForViewPlayer() {
		return isForViewPlayer;
	}

	public void setCardBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setCardText(String text) {
		cardRank = text;
	}

	public void setCardTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public void setCardTitle(String title) {
		cardColor = title;
	}

	public void setDiscardButton(MaterialLink discardButton) {
		this.discardButton = discardButton;
	}

	public void setPlayButton(MaterialLink playButton) {
		this.playButton = playButton;
	}

	public void showCardColor() {
		cardColorDisplayed = true;
		cardColorContainer.setTextColor(textColor);
		setBackgroundColor(backgroundColor);
		if (cardRankDisplayed) {
			cardRankContainer.setTextColor(textColor);
		}
	}

	public void showCardRank() {
		cardRankDisplayed = true;
		if (!cardColorDisplayed) {
			cardRankContainer.setTextColor(Color.WHITE);
		} else {
			cardRankContainer.setTextColor(textColor);
		}
	}

	public boolean isCardColorDisplayed() {
		return cardColorDisplayed;
	}

	public boolean isCardRankDisplayed() {
		return cardRankDisplayed;
	}

	public HanabiPopupPanel getCardMenu() {
		return cardMenu;
	}

}
