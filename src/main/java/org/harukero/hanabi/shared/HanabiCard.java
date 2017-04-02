package org.harukero.hanabi.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import gwt.material.design.client.constants.Color;

public class HanabiCard implements IsSerializable {
	private Color color;
	private Integer number;
	private boolean colorKnown;
	private boolean numberKnown;

	@SuppressWarnings("unused")
	private HanabiCard() {
		this(Color.BLACK, 666);
	}

	public HanabiCard(Color color, Integer number) {
		this.color = color;
		this.number = number;
		colorKnown = false;
		numberKnown = true;
	}

	public Color getColor() {
		return color;
	}

	public Integer getNumber() {
		return number;
	}

	public boolean inverseColorStatus() {
		colorKnown = !colorKnown;
		return colorKnown;
	}

	public boolean inverseNumberStatus() {
		numberKnown = !numberKnown;
		return numberKnown;
	}

	public boolean isColorKnown() {
		return colorKnown;
	}

	public boolean isNumberKnown() {
		return numberKnown;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setColorKnown(boolean colorKnown) {
		this.colorKnown = colorKnown;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setNumberKnown(boolean numberKnown) {
		this.numberKnown = numberKnown;
	}

}
