package org.harukero.hanabi.client.views;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialRow;

public class LifeAndInfosTokenContainerView extends MaterialRow {
	List<MaterialIcon> lifes = new ArrayList<>();
	List<MaterialIcon> infos = new ArrayList<>();

	public LifeAndInfosTokenContainerView() {
		this.setGrid("s12");
		this.setTextAlign(TextAlign.CENTER);
		init();
	}

	public void addNewInfo() {
		MaterialIcon life = new MaterialIcon(IconType.INFO);
		life.setIconSize(IconSize.MEDIUM);
		life.setTextColor(Color.BLUE);
		lifes.add(life);
		this.add(life);
	}

	public void addNewLife() {
		MaterialIcon info = new MaterialIcon(IconType.FAVORITE);
		info.setIconSize(IconSize.MEDIUM);
		info.setTextColor(Color.RED);
		infos.add(info);
		this.add(info);
	}

	private void init() {
		this.clear();
		lifes.clear();
		infos.clear();
		IntStream.rangeClosed(1, 3).forEach(item -> addNewLife());
		IntStream.rangeClosed(1, 8).forEach(item -> addNewInfo());
	}

	public void removeInfo() {
		if (!infos.isEmpty()) {
			MaterialIcon info = infos.get(0);
			this.remove(info);
			infos.remove(info);
		}
	}

	public void removeLife() {
		if (!lifes.isEmpty()) {
			MaterialIcon life = lifes.get(0);
			this.remove(life);
			lifes.remove(life);
		}
	}

	public void reset() {
		this.clear();
		lifes.clear();
		infos.clear();
	}

}
