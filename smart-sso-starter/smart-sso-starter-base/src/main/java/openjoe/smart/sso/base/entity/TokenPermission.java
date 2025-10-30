package openjoe.smart.sso.base.entity;

import java.util.List;
import java.util.Set;

/**
 * Permission info for the logged-in user
 *  * @author huynx */
public class TokenPermission {

	/**
	 * Granted permissions
	 */
	private Set<String> permissionSet;

	/**
	 * Not-granted permissions (used to hide menus/buttons on the frontend)
	 */
	private Set<String> noPermissionSet;

	/**
	 * Menus associated with granted permissions
	 */
	private List<TokenMenu> menuList;

	public TokenPermission() {
	}

	public TokenPermission(Set<String> permissionSet, Set<String> noPermissionSet) {
		this.permissionSet = permissionSet;
		this.noPermissionSet = noPermissionSet;
	}

	public TokenPermission(Set<String> permissionSet, Set<String> noPermissionSet, List<TokenMenu> menuList) {
		this.permissionSet = permissionSet;
		this.noPermissionSet = noPermissionSet;
		this.menuList = menuList;
	}

	public Set<String> getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(Set<String> permissionSet) {
		this.permissionSet = permissionSet;
	}

	public Set<String> getNoPermissionSet() {
		return noPermissionSet;
	}

	public void setNoPermissionSet(Set<String> noPermissionSet) {
		this.noPermissionSet = noPermissionSet;
	}

	public List<TokenMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<TokenMenu> menuList) {
		this.menuList = menuList;
	}
}
