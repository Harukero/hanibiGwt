package org.harukero.hanabi.client.views;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardAction;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialCollectionItem;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;

public class CardZoneView extends MaterialColumn {

	private MaterialCard card;
	private MaterialCardTitle cardTitle;
	private MaterialCardAction cardAction;
	private MaterialCollection cardCollection;
	private MaterialCardContent cardContent;
	private MaterialLabel cardContentLabel;

	public CardZoneView() {
		setGrid("s2");
		card = new MaterialCard();
		cardContent = new MaterialCardContent();
		cardTitle = new MaterialCardTitle();
		cardContent.add(cardTitle);
		cardContentLabel = new MaterialLabel();
		cardContent.add(cardContentLabel);
		card.add(cardContent);

		cardAction = new MaterialCardAction();
		cardCollection = new MaterialCollection();
		cardAction.add(cardCollection);
		card.add(cardAction);
		this.add(card);
	}

	public CardZoneView setCardBackgroundColor(Color color) {
		card.setBackgroundColor(color);
		return this;
	}

	public CardZoneView setCardTextColor(Color color) {
		card.setTextColor(color);
		return this;
	}

	public CardZoneView setCardTitleText(String text) {
		cardTitle.setText(text);
		return this;
	}

	public CardZoneView setCardLabelText(String text) {
		cardContentLabel.setText(text);
		return this;
	}

	public CardZoneView addNewCardItem(int number) {
		MaterialCollectionItem item = new MaterialCollectionItem();
		item.add(new MaterialLabel("card number " + number));
		cardCollection.add(item);
		return this;
	}

	public void clearCollection() {
		cardCollection.clear();
	}
}
