/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.harukero.hanabi.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface AppResources extends ClientBundle {
	interface Normalize extends CssResource {
	}

	AppResources resources = GWT.create(AppResources.class);
	
	interface Style extends CssResource {

		String cardButton();

		String fontSize14();

		String hanabiChip();

		String hanabiCard();

		String playerName();

		String playerZoneView();

		String hanabiPopupMenu();

	}

	@Source("css/normalize.gss")
	Normalize normalize();

	@Source("css/style.gss")
	Style style();

	@Source("images/black.png")
	ImageResource black();

	@Source("images/k-1.png")
	ImageResource black_1();

	@Source("images/k-2.png")
	ImageResource black_2();

	@Source("images/k-3.png")
	ImageResource black_3();

	@Source("images/k-4.png")
	ImageResource black_4();

	@Source("images/k-5.png")
	ImageResource black_5();

	@Source("images/blue.png")
	ImageResource blue();

	@Source("images/b-1.png")
	ImageResource blue_1();

	@Source("images/b-2.png")
	ImageResource blue_2();

	@Source("images/b-3.png")
	ImageResource blue_3();

	@Source("images/b-4.png")
	ImageResource blue_4();

	@Source("images/b-5.png")
	ImageResource blue_5();

	@Source("images/green.png")
	ImageResource green();

	@Source("images/g-1.png")
	ImageResource green_1();

	@Source("images/g-2.png")
	ImageResource green_2();

	@Source("images/g-3.png")
	ImageResource green_3();

	@Source("images/g-4.png")
	ImageResource green_4();

	@Source("images/g-5.png")
	ImageResource green_5();

	@Source("images/heart.png")
	ImageResource heart();

	@Source("images/info.png")
	ImageResource information();

	@Source("images/number.png")
	ImageResource number();

	@Source("images/discard.png")
	ImageResource discard();

	@Source("images/play.png")
	ImageResource play();

	@Source("images/red.png")
	ImageResource red();

	@Source("images/r-1.png")
	ImageResource red_1();

	@Source("images/r-2.png")
	ImageResource red_2();

	@Source("images/r-3.png")
	ImageResource red_3();

	@Source("images/r-4.png")
	ImageResource red_4();

	@Source("images/r-5.png")
	ImageResource red_5();

	@Source("images/logo.png")
	ImageResource logo();

	@Source("images/white.png")
	ImageResource white();

	@Source("images/w-1.png")
	ImageResource white_1();

	@Source("images/w-2.png")
	ImageResource white_2();

	@Source("images/w-3.png")
	ImageResource white_3();

	@Source("images/w-4.png")
	ImageResource white_4();

	@Source("images/w-5.png")
	ImageResource white_5();

	@Source("images/yellow.png")
	ImageResource yellow();

	@Source("images/y-1.png")
	ImageResource yellow_1();

	@Source("images/y-2.png")
	ImageResource yellow_2();

	@Source("images/y-3.png")
	ImageResource yellow_3();

	@Source("images/y-4.png")
	ImageResource yellow_4();

	@Source("images/y-5.png")
	ImageResource yellow_5();

	@Source("images/color.png")
	ImageResource color();

}
