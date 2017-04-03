package org.harukero.hanabi.client.utils;

import org.harukero.hanabi.client.i18n.HanabiTextConstants;
import org.harukero.hanabi.client.resources.AppResources;

import com.google.gwt.core.client.GWT;

public class ViewUtils {
	public static final AppResources RESOURCES = GWT.create(AppResources.class);
	public static final HanabiTextConstants CONSTANTS = GWT.create(HanabiTextConstants.class);

}
