package org.harukero.hanabi.client.utils;

import java.util.Arrays;
import java.util.List;

import org.harukero.hanabi.client.resources.AppResources;

import com.google.gwt.core.client.GWT;

import gwt.material.design.client.constants.Color;

public class ViewUtils {
	public static final AppResources RESOURCES = GWT.create(AppResources.class);

	public static final List<Color> HANABI_COLORS = Arrays
			.asList(new Color[] { Color.RED, Color.GREEN, Color.BLUE, Color.WHITE, Color.YELLOW });

}
