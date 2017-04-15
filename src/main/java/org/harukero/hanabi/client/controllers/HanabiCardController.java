package org.harukero.hanabi.client.controllers;

import org.harukero.hanabi.client.application.IParent;
import org.harukero.hanabi.client.rpc.HanabiAsyncCallback;
import org.harukero.hanabi.client.rpc.HanabiStateUpdaterService;
import org.harukero.hanabi.client.rpc.HanabiStateUpdaterServiceAsync;
import org.harukero.hanabi.client.views.HanabiCardView;
import org.harukero.hanabi.client.views.HanabiModal;
import org.harukero.hanabi.shared.core.HanabiAction;
import org.harukero.hanabi.shared.core.HanabiActionBuilder;
import org.harukero.hanabi.shared.core.HanabiCard;
import org.harukero.hanabi.shared.core.HanabiState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class HanabiCardController {
	// private static final Logger logger = Logger.getLogger("HanabiLogger");
	private HanabiState model;
	private HanabiCard cardModel;
	private HanabiCardView view;

	private HanabiStateUpdaterServiceAsync updateState = GWT.create(HanabiStateUpdaterService.class);
	private IParent parent;

	public HanabiCardController(IParent parent, HanabiState model, HanabiCard cardModel, HanabiCardView view) {
		this.parent = parent;
		this.model = model;
		this.cardModel = cardModel;
		this.view = view;
		bind();
		if (cardModel.isColorKnown()) {
			showCardColor();
		}
		if (cardModel.isRankKnown()) {
			showCardRank();
		}
	}

	private void bind() {
		bindDiscardButton();
		bindPlayButton();
		bindInfoColorButton();
		bindInfoRankButton();
	}

	private void bindDiscardButton() {
		HasClickHandlers discardButton = view.getDiscardButton();
		if (discardButton != null) {
			discardButton.addClickHandler(event -> {
				view.getCardMenu().hide();
				AsyncCallback<HanabiState> callback = new HanabiAsyncCallback<HanabiState>() {

					@Override
					public void onSuccess(HanabiState result) {
						// logger.log(Level.INFO, "Card discarded");
						// MaterialToast.fireToast("Card discarded");
						parent.update(result);
					}
				};

				updateState.updateHanabiState(createActionForDiscard(), model, callback);
			});
		}
	}

	private void bindInfoColorButton() {
		HasClickHandlers infoColorButton = view.getColorInfoButton();
		if (infoColorButton != null) {
			infoColorButton.addClickHandler(event -> {
				view.getCardMenu().hide();
				if (model.getInfoLeft() > 0) {
					AsyncCallback<HanabiState> callback = new HanabiAsyncCallback<HanabiState>() {

						@Override
						public void onSuccess(HanabiState result) {
							// logger.log(Level.INFO, "Card played");
							// MaterialToast.fireToast("Card played");
							parent.update(result);
						}
					};
					updateState.updateHanabiState(createActionForInfoColor(), model, callback);
				} else {
					HanabiModal.openModal("Sorry", "You don't have information token remaining");
				}
			});

		}
	}

	private void bindInfoRankButton() {
		HasClickHandlers infoRankButton = view.getRankInfoButton();
		if (infoRankButton != null) {
			infoRankButton.addClickHandler(event -> {
				view.getCardMenu().hide();
				if (model.getInfoLeft() > 0) {
					AsyncCallback<HanabiState> callback = new HanabiAsyncCallback<HanabiState>() {

						@Override
						public void onSuccess(HanabiState result) {
							// logger.log(Level.INFO, "Card played");
							// MaterialToast.fireToast("Card played");
							parent.update(result);
						}
					};
					updateState.updateHanabiState(createActionForInfoRank(), model, callback);
				} else {
					HanabiModal.openModal("Sorry", "You don't have information token remaining");
				}
			});
		}

	}

	private void bindPlayButton() {
		HasClickHandlers playButton = view.getPlayButton();
		if (playButton != null) {
			playButton.addClickHandler(event -> {
				view.getCardMenu().hide();
				AsyncCallback<HanabiState> callback = new HanabiAsyncCallback<HanabiState>() {

					@Override
					public void onSuccess(HanabiState result) {
						// logger.log(Level.INFO, "Card played");
						// MaterialToast.fireToast("Card played");
						parent.update(result);
					}
				};
				updateState.updateHanabiState(createActionForPlay(), model, callback);
			});
		}
	}

	private HanabiAction createActionForDiscard() {
		return HanabiActionBuilder.createDiscardAction(cardModel, cardModel.getOwner());
	}

	private HanabiAction createActionForInfoColor() {
		return HanabiActionBuilder.createInfoColorAction(cardModel.getColor(), cardModel.getOwner());
	}

	private HanabiAction createActionForInfoRank() {
		return HanabiActionBuilder.createInfoRankAction(cardModel.getRank(), cardModel.getOwner());
	}

	private HanabiAction createActionForPlay() {
		return HanabiActionBuilder.createPlayAction(cardModel, cardModel.getOwner());
	}

	public HanabiCardView getView() {
		return view;
	}

	public void showCardRank() {
		view.showCardRank();
	}

	public void showCardColor() {
		view.showCardColor();
	}
}
