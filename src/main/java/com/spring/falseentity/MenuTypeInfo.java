package com.spring.falseentity;

import java.util.List;

import com.spring.entity.Menu;

public class MenuTypeInfo {
	private String menuTypeId;
	private String menuTypeName;
	private String menuTypeIcon;
	private List<Menu> menus;
	public String getMenuTypeId() {
		return menuTypeId;
	}
	public void setMenuTypeId(String menuTypeId) {
		this.menuTypeId = menuTypeId;
	}
	public String getMenuTypeName() {
		return menuTypeName;
	}
	public void setMenuTypeName(String menuTypeName) {
		this.menuTypeName = menuTypeName;
	}
	public String getMenuTypeIcon() {
		return menuTypeIcon;
	}
	public void setMenuTypeIcon(String menuTypeIcon) {
		this.menuTypeIcon = menuTypeIcon;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
}
