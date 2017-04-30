package org.harukero.hanabi.client.views;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.harukero.hanabi.client.utils.ViewUtils;

import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialRow;

public class LifeAndInfosTokenContainerView extends MaterialRow {
	List<MaterialImage> lifes = new ArrayList<>();
	List<MaterialImage> infos = new ArrayList<>();

	public LifeAndInfosTokenContainerView() {
		setGrid("s12");
		setTextAlign(TextAlign.CENTER);
		init();
	}

	public void addNewLife() {
		MaterialImage life = new MaterialImage(ViewUtils.RESOURCES.heart());
		lifes.add(life);
		this.add(life);
	}

	public void addNewInfo() {
		MaterialImage info = new MaterialImage(ViewUtils.RESOURCES.information());
		infos.add(info);
		this.add(info);
	}

	private void init() {
		clear();
		lifes.clear();
		infos.clear();
		IntStream.rangeClosed(1, 3).forEach(item -> addNewLife());
		IntStream.rangeClosed(1, 8).forEach(item -> addNewInfo());
	}

	public void removeInfo() {
		if (!infos.isEmpty()) {
			MaterialImage info = infos.get(0);
			this.remove(info);
			infos.remove(info);
		}
	}

	public void removeLife() {
		if (!lifes.isEmpty()) {
			MaterialImage life = lifes.get(0);
			this.remove(life);
			lifes.remove(life);
		}
	}

	public void reset() {
		clear();
		lifes.clear();
		infos.clear();
	}

}
