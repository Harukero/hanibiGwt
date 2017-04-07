package org.harukero.hanabi.shared.core;

import com.google.gwt.user.client.rpc.IsSerializable;

import gwt.material.design.client.constants.Color;

public class HanabiCard implements IsSerializable {
	private Color color;
	private Integer number;
	private boolean colorKnown;
	private boolean numberKnown;
	private int itemId;
	private String fullId;
	private int owner;

	@SuppressWarnings("unused")
	private HanabiCard() {
	}

	public HanabiCard(Color color, Integer number, int itemId) {
		this.color = color;
		this.number = number;
		this.setItemId(itemId);
		colorKnown = false;
		numberKnown = true;
		setCardFullId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HanabiCard other = (HanabiCard) obj;
		if (fullId == null) {
			if (other.fullId != null)
				return false;
		} else if (!fullId.equals(other.fullId))
			return false;
		return true;
	}

	public String getCardFullId() {
		return fullId;
	}

	public Color getColor() {
		return color;
	}

	public int getItemId() {
		return itemId;
	}

	public Integer getNumber() {
		return number;
	}

	public int getOwner() {
		return owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (colorKnown ? 1231 : 1237);
		result = prime * result + ((fullId == null) ? 0 : fullId.hashCode());
		result = prime * result + (numberKnown ? 1231 : 1237);
		return result;
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

	public void setCardFullId() {
		fullId = color.getCssName() + "-" + number + "-" + itemId;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setColorKnown(boolean colorKnown) {
		this.colorKnown = colorKnown;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setNumberKnown(boolean numberKnown) {
		this.numberKnown = numberKnown;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "HanabiCard [colorKnown=" + colorKnown + ", numberKnown=" + numberKnown + ", fullId=" + fullId + "]";
	}

}
