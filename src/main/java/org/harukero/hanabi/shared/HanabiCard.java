package org.harukero.hanabi.shared;

import gwt.material.design.client.constants.Color;

public class HanabiCard {
	private Color color;
	private Integer number;
	private boolean colorKnown;
	private boolean numberKnown;

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
}
