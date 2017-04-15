package org.harukero.hanabi.client.views;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class HanabiPopupPanel extends PopupPanel {

	public HanabiPopupPanel(Widget widget) {
		// PopupPanel's constructor takes 'auto-hide' as its boolean parameter.
		// If this is set, the panel closes itself automatically when the user
		// clicks outside of it.
		super(true);

		// PopupPanel is a SimplePanel, so you have to set it's widget property
		// to
		// whatever you want its contents to be.
		setWidget(widget);
	}
}
