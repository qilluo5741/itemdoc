package com.spring.falseentity;

import java.io.Serializable;
/**
 * 查询菜单
 * @author Administrator
 */
public class MenusDto  implements  Serializable{
	private static final long serialVersionUID = 1L;
	private String menuId;
	private String menuName;
	private String menuTypeName;
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuTypeName() {
		return menuTypeName;
	}
	public void setMenuTypeName(String menuTypeName) {
		this.menuTypeName = menuTypeName;
	}
	
}
