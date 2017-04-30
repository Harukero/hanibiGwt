package org.harukero.hanabi.client.views;

import org.harukero.hanabi.client.utils.ViewUtils;

import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.ImageResource;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialCollectionItem;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialPanel;

public class HanabiCardView extends MaterialPanel implements HasAllMouseHandlers {

	public static final ImageResource blackCard = ViewUtils.RESOURCES.black();

	public static HanabiCardView createCardForColor(Color color, Integer rank, boolean isForViewPlayer) {
		switch (color) {
		case RED:
			switch (rank) {
			case 1:
				return new HanabiCardView(Color.RED, rank, ViewUtils.RESOURCES.red_1(), ViewUtils.RESOURCES.black_1(),
						ViewUtils.RESOURCES.red(), isForViewPlayer);
			case 2:
				return new HanabiCardView(Color.RED, rank, ViewUtils.RESOURCES.red_2(), ViewUtils.RESOURCES.black_2(),
						ViewUtils.RESOURCES.red(), isForViewPlayer);
			case 3:
				return new HanabiCardView(Color.RED, rank, ViewUtils.RESOURCES.red_3(), ViewUtils.RESOURCES.black_3(),
						ViewUtils.RESOURCES.red(), isForViewPlayer);
			case 4:
				return new HanabiCardView(Color.RED, rank, ViewUtils.RESOURCES.red_4(), ViewUtils.RESOURCES.black_4(),
						ViewUtils.RESOURCES.red(), isForViewPlayer);
			case 5:
				return new HanabiCardView(Color.RED, rank, ViewUtils.RESOURCES.red_5(), ViewUtils.RESOURCES.black_5(),
						ViewUtils.RESOURCES.red(), isForViewPlayer);
			default:
				break;
			}
		case GREEN:
			switch (rank) {
			case 1:
				return new HanabiCardView(Color.GREEN, rank, ViewUtils.RESOURCES.green_1(),
						ViewUtils.RESOURCES.black_1(), ViewUtils.RESOURCES.green(), isForViewPlayer);
			case 2:
				return new HanabiCardView(Color.GREEN, rank, ViewUtils.RESOURCES.green_2(),
						ViewUtils.RESOURCES.black_2(), ViewUtils.RESOURCES.green(), isForViewPlayer);
			case 3:
				return new HanabiCardView(Color.GREEN, rank, ViewUtils.RESOURCES.green_3(),
						ViewUtils.RESOURCES.black_3(), ViewUtils.RESOURCES.green(), isForViewPlayer);
			case 4:
				return new HanabiCardView(Color.GREEN, rank, ViewUtils.RESOURCES.green_4(),
						ViewUtils.RESOURCES.black_4(), ViewUtils.RESOURCES.green(), isForViewPlayer);
			case 5:
				return new HanabiCardView(Color.GREEN, rank, ViewUtils.RESOURCES.green_5(),
						ViewUtils.RESOURCES.black_5(), ViewUtils.RESOURCES.green(), isForViewPlayer);
			default:
				break;
			}

		case BLUE:
			switch (rank) {
			case 1:
				return new HanabiCardView(Color.BLUE, rank, ViewUtils.RESOURCES.blue_1(), ViewUtils.RESOURCES.black_1(),
						ViewUtils.RESOURCES.blue(), isForViewPlayer);
			case 2:
				return new HanabiCardView(Color.BLUE, rank, ViewUtils.RESOURCES.blue_2(), ViewUtils.RESOURCES.black_2(),
						ViewUtils.RESOURCES.blue(), isForViewPlayer);
			case 3:
				return new HanabiCardView(Color.BLUE, rank, ViewUtils.RESOURCES.blue_3(), ViewUtils.RESOURCES.black_3(),
						ViewUtils.RESOURCES.blue(), isForViewPlayer);
			case 4:
				return new HanabiCardView(Color.BLUE, rank, ViewUtils.RESOURCES.blue_4(), ViewUtils.RESOURCES.black_4(),
						ViewUtils.RESOURCES.blue(), isForViewPlayer);
			case 5:
				return new HanabiCardView(Color.BLUE, rank, ViewUtils.RESOURCES.blue_5(), ViewUtils.RESOURCES.black_5(),
						ViewUtils.RESOURCES.blue(), isForViewPlayer);
			default:
				break;
			}
		case WHITE:
			switch (rank) {
			case 1:
				return new HanabiCardView(Color.WHITE, rank, ViewUtils.RESOURCES.white_1(),
						ViewUtils.RESOURCES.black_1(), ViewUtils.RESOURCES.white(), isForViewPlayer);
			case 2:
				return new HanabiCardView(Color.WHITE, rank, ViewUtils.RESOURCES.white_2(),
						ViewUtils.RESOURCES.black_2(), ViewUtils.RESOURCES.white(), isForViewPlayer);
			case 3:
				return new HanabiCardView(Color.WHITE, rank, ViewUtils.RESOURCES.white_3(),
						ViewUtils.RESOURCES.black_3(), ViewUtils.RESOURCES.white(), isForViewPlayer);
			case 4:
				return new HanabiCardView(Color.WHITE, rank, ViewUtils.RESOURCES.white_4(),
						ViewUtils.RESOURCES.black_4(), ViewUtils.RESOURCES.white(), isForViewPlayer);
			case 5:
				return new HanabiCardView(Color.WHITE, rank, ViewUtils.RESOURCES.white_5(),
						ViewUtils.RESOURCES.black_5(), ViewUtils.RESOURCES.white(), isForViewPlayer);
			default:
				break;
			}
		case YELLOW:
			switch (rank) {
			case 1:
				return new HanabiCardView(Color.YELLOW, rank, ViewUtils.RESOURCES.yellow_1(),
						ViewUtils.RESOURCES.black_1(), ViewUtils.RESOURCES.yellow(), isForViewPlayer);
			case 2:
				return new HanabiCardView(Color.YELLOW, rank, ViewUtils.RESOURCES.yellow_2(),
						ViewUtils.RESOURCES.black_2(), ViewUtils.RESOURCES.yellow(), isForViewPlayer);
			case 3:
				return new HanabiCardView(Color.YELLOW, rank, ViewUtils.RESOURCES.yellow_3(),
						ViewUtils.RESOURCES.black_3(), ViewUtils.RESOURCES.yellow(), isForViewPlayer);
			case 4:
				return new HanabiCardView(Color.YELLOW, rank, ViewUtils.RESOURCES.yellow_4(),
						ViewUtils.RESOURCES.black_4(), ViewUtils.RESOURCES.yellow(), isForViewPlayer);
			case 5:
				return new HanabiCardView(Color.YELLOW, rank, ViewUtils.RESOURCES.yellow_5(),
						ViewUtils.RESOURCES.black_5(), ViewUtils.RESOURCES.yellow(), isForViewPlayer);
			default:
				break;
			}
		default:
			return null;
		}

	}

	private Color cardColor;
	private Integer cardRank;
	private MaterialImage discardButton;
	private MaterialImage playButton;
	private boolean isForViewPlayer;
	private MaterialImage rankInfoButton;
	private MaterialImage colorInfoButton;
	private boolean cardColorDisplayed;
	private boolean cardRankDisplayed;
	private HanabiPopupPanel cardMenu;
	private MaterialImage cardImage;
	private ImageResource justRank;
	private ImageResource justColor;
	private ImageResource fullCard;

	private HanabiCardView(Color cardColor, Integer cardRank, ImageResource fullCard, ImageResource justRank,
			ImageResource justColor, boolean isForViewPlayer) {
		this.cardColor = cardColor;
		this.cardRank = cardRank;
		this.fullCard = fullCard;
		this.justRank = justRank;
		this.justColor = justColor;
		this.isForViewPlayer = isForViewPlayer;
		if (isForViewPlayer) {
			cardImage = new MaterialImage(blackCard);
		} else {
			cardImage = new MaterialImage(fullCard);
		}
		this.add(cardImage);
		buildActionMenu(isForViewPlayer);
		setMenuClickHandler();
	}

	private void setMenuClickHandler() {
		cardImage.addClickHandler(event -> {
			cardMenu.setPopupPositionAndShow((offsetWidth, offsetHeight) -> {
				int left = event.getClientX();
				int top = event.getClientY();
				cardMenu.setPopupPosition(left, top);
			});
		});
	}

	private void buildActionMenu(boolean isForViewPlayer) {
		MaterialCollection action = new MaterialCollection();
		action.setStyleName(ViewUtils.RESOURCES.style().hanabiPopupMenu());
		MaterialCollectionItem item;
		if (isForViewPlayer) {
			item = new MaterialCollectionItem();
			playButton = new MaterialImage(ViewUtils.RESOURCES.play());
			item.add(playButton);
			item.setStyleName(ViewUtils.RESOURCES.style().hanabiPopupMenu());
			item.setWidth(playButton.getWidth() + "px");
			item.setHeight(playButton.getHeight() + "px");
			action.add(item);

			item = new MaterialCollectionItem();
			discardButton = new MaterialImage(ViewUtils.RESOURCES.discard());
			item.add(discardButton);
			item.setStyleName(ViewUtils.RESOURCES.style().hanabiPopupMenu());
			item.setWidth(discardButton.getWidth() + "px");
			item.setHeight(discardButton.getHeight() + "px");
			action.add(item);
		} else {
			item = new MaterialCollectionItem();
			colorInfoButton = new MaterialImage(ViewUtils.RESOURCES.color());
			colorInfoButton.setWidth("121px");
			colorInfoButton.setHeight("40px");
			item.add(colorInfoButton);
			item.setStyleName(ViewUtils.RESOURCES.style().hanabiPopupMenu());
			item.setWidth("121px");
			item.setHeight("40px");
			action.add(item);

			item = new MaterialCollectionItem();
			rankInfoButton = new MaterialImage(ViewUtils.RESOURCES.number());
			item.add(rankInfoButton);
			item.setStyleName(ViewUtils.RESOURCES.style().hanabiPopupMenu());
			item.setWidth(rankInfoButton.getWidth() + "px");
			item.setHeight(rankInfoButton.getHeight() + "px");
			action.add(item);
		}
		cardMenu = new HanabiPopupPanel(action);
	}

	public Integer getCardText() {
		return cardRank;
	}

	public Color getCardTitle() {
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

	public void setCardText(Integer text) {
		cardRank = text;
	}

	public void setCardTitle(Color title) {
		cardColor = title;
	}

	public void setDiscardButton(MaterialImage discardButton) {
		this.discardButton = discardButton;
	}

	public void setPlayButton(MaterialImage playButton) {
		this.playButton = playButton;
	}

	public void showCardRank() {
		cardRankDisplayed = true;
		if (isForViewPlayer) {
			this.remove(cardImage);
			if (cardColorDisplayed) {
				cardImage = new MaterialImage(fullCard);
			} else {
				cardImage = new MaterialImage(justRank);
			}
			this.add(cardImage);
			setMenuClickHandler();
		}
	}

	public void showCardColor() {
		cardColorDisplayed = true;
		if (isForViewPlayer) {
			this.remove(cardImage);
			if (cardRankDisplayed) {
				cardImage = new MaterialImage(fullCard);
			} else {
				cardImage = new MaterialImage(justColor);
			}
			this.add(cardImage);
			setMenuClickHandler();
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
