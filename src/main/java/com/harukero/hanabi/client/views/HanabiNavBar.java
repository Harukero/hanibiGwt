package com.harukero.hanabi.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HanabiNavBar extends Composite {

	private static HanabiNavBarUiBinder uiBinder = GWT.create(HanabiNavBarUiBinder.class);

	interface HanabiNavBarUiBinder extends UiBinder<Widget, HanabiNavBar> {
	}

	public HanabiNavBar() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
