package com.harukero.hanabi.shared.core;

import com.google.gwt.user.client.rpc.IsSerializable;

import gwt.material.design.client.constants.Color;

public class HanabiCard implements IsSerializable {
	private Color color;
	private Integer rank;
	private boolean colorKnown;
	private boolean rankKnown;
	private int itemId;
	private String fullId;
	private int owner;

	@SuppressWarnings("unused")
	private HanabiCard() {
	}

	public HanabiCard(Color color, Integer number, int itemId) {
		this.color = color;
		rank = number;
		setItemId(itemId);
		colorKnown = false;
		rankKnown = false;
		setCardFullId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		HanabiCard other = (HanabiCard) obj;
		if (fullId == null) {
			if (other.fullId != null) {
				return false;
			}
		} else if (!fullId.equals(other.fullId)) {
			return false;
		}
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

	public Integer getRank() {
		return rank;
	}

	public int getOwner() {
		return owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (colorKnown ? 1231 : 1237);
		result = prime * result + (fullId == null ? 0 : fullId.hashCode());
		result = prime * result + (rankKnown ? 1231 : 1237);
		return result;
	}

	public boolean inverseColorStatus() {
		colorKnown = !colorKnown;
		return colorKnown;
	}

	public boolean inverseRankStatus() {
		rankKnown = !rankKnown;
		return rankKnown;
	}

	public boolean isColorKnown() {
		return colorKnown;
	}

	public boolean isRankKnown() {
		return rankKnown;
	}

	public void setCardFullId() {
		fullId = color.getCssName() + "-" + rank + "-" + itemId;
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

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public void setRankKnown(boolean rankKnown) {
		this.rankKnown = rankKnown;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "HanabiCard [colorKnown=" + colorKnown + ", numberKnown=" + rankKnown + ", fullId=" + fullId + "]";
	}

}
