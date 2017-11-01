package com.bjsxt.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单列表
 */
public class Menu implements Serializable {
	private Integer menuId; // 菜单id
	private String showText; // 菜单栏显示列表
	private String location; // 菜单栏跳转地址
	private List<Menu> childMenu;

	public Menu(){

	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getShowText() {
		return showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Menu> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(List<Menu> childMenu) {
		this.childMenu = childMenu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((childMenu == null) ? 0 : childMenu.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		result = prime * result + ((showText == null) ? 0 : showText.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (childMenu == null) {
			if (other.childMenu != null)
				return false;
		} else if (!childMenu.equals(other.childMenu))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		if (showText == null) {
			if (other.showText != null)
				return false;
		} else if (!showText.equals(other.showText))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", showText=" + showText + ", location=" + location + ", childMenu="
				+ childMenu + "]";
	}
	
}
