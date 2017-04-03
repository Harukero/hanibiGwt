package org.harukero.hanabi.client.views;

import com.google.gwt.user.client.ui.RootPanel;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialModalContent;
import gwt.material.design.client.ui.MaterialModalFooter;
import gwt.material.design.client.ui.MaterialTitle;

public class HanabiModal extends MaterialModal {

	/*
	 * <m:MaterialModal type="DEFAULT" dismissable="false" inDuration="500"
	 * outDuration="500"> <m:MaterialModalContent> <m:MaterialTitle
	 * title="Default Modal"
	 * description="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
	 * /> </m:MaterialModalContent> <m:MaterialModalFooter> <m:MaterialButton
	 * text="Close Modal" type="FLAT"/> </m:MaterialModalFooter>
	 * </m:MaterialModal>
	 */

	public static void openModal(String modalTitle, String modalContent) {
		HanabiModal modal = new HanabiModal(modalTitle, modalContent);
		RootPanel.get().add(modal);
		modal.open();
	}

	private MaterialModalContent contentPanel;
	private MaterialTitle content;
	private MaterialModalFooter footerPanel;

	private MaterialButton closeButton;

	private HanabiModal(String modalTitle, String modalContent) {
		content = new MaterialTitle(modalTitle, modalContent);
		contentPanel = new MaterialModalContent();
		contentPanel.add(content);

		closeButton = new MaterialButton("Close");
		footerPanel = new MaterialModalFooter();
		footerPanel.add(closeButton);

		closeButton.addClickHandler(event -> {
			HanabiModal.this.close();
			RootPanel.get().remove(HanabiModal.this);
		});

		this.add(contentPanel);
		this.add(footerPanel);
	}

}
