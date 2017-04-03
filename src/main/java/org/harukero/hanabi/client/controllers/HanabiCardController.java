package org.harukero.hanabi.client.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.harukero.hanabi.client.application.IParent;
import org.harukero.hanabi.client.rpc.HanabiAsyncCallback;
import org.harukero.hanabi.client.rpc.HanabiStateUpdaterService;
import org.harukero.hanabi.client.rpc.HanabiStateUpdaterServiceAsync;
import org.harukero.hanabi.client.views.HanabiCardView;
import org.harukero.hanabi.shared.core.HanabiAction;
import org.harukero.hanabi.shared.core.HanabiActionBuilder;
import org.harukero.hanabi.shared.core.HanabiCard;
import org.harukero.hanabi.shared.core.HanabiState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;

import gwt.material.design.client.ui.MaterialToast;

public class HanabiCardController {
	private static final Logger logger = Logger.getLogger("HanabiLogger");
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
	}

	private void bind() {
		bindDiscardButton();
		bindPlayButton();
		bindColorInfoButton();
		bindRankInfoButton();
	}

	private void bindColorInfoButton() {
		HasClickHandlers colorInfoButton = view.getColorInfoButton();
		if (colorInfoButton != null) {
			// bind it
		}
	}

	private void bindDiscardButton() {
		HasClickHandlers discardButton = view.getDiscardButton();
		if (discardButton != null) {
			discardButton.addClickHandler(event -> {
				AsyncCallback<HanabiState> callback = new HanabiAsyncCallback<HanabiState>() {

					@Override
					public void onSuccess(HanabiState result) {
						logger.log(Level.INFO, "Card discarded");
						MaterialToast.fireToast("Card discarded");
						parent.update(result);
					}
				};

				updateState.updateHanabiState(createActionForDiscard(), model, callback);
			});
		}
	}

	private void bindPlayButton() {
		HasClickHandlers playButton = view.getPlayButton();
		if (playButton != null) {
			playButton.addClickHandler(event -> {
				AsyncCallback<HanabiState> callback = new HanabiAsyncCallback<HanabiState>() {

					@Override
					public void onSuccess(HanabiState result) {
						logger.log(Level.INFO, "Card played");
						MaterialToast.fireToast("Card played");
						parent.update(result);
					}
				};
				updateState.updateHanabiState(createActionForPlay(), model, callback);
			});
		}
	}

	private void bindRankInfoButton() {
		HasClickHandlers rankInfoButton = view.getRankInfoButton();
		if (rankInfoButton != null) {
			// bind it
		}

	}

	private HanabiAction createActionForDiscard() {
		return HanabiActionBuilder.createDiscardAction(cardModel, 1);
	}

	private HanabiAction createActionForPlay() {
		return HanabiActionBuilder.createPlayAction(cardModel, 1);
	}

}
