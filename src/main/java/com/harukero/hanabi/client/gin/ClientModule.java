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
package com.harukero.hanabi.client.gin;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.harukero.hanabi.client.resources.AppResources;
import com.mvp4g.client.Mvp4gModule;

public class ClientModule implements EntryPoint {
    
	  @Override
	  public void onModuleLoad() {
	    AppResources.resources.style().ensureInjected();
	    AppResources.resources.normalize().ensureInjected();
	    
	    Mvp4gModule module = GWT.create(Mvp4gModule.class);
	    module.createAndStartModule();
	    RootLayoutPanel.get().add((Widget) module.getStartView());
	  }

}
