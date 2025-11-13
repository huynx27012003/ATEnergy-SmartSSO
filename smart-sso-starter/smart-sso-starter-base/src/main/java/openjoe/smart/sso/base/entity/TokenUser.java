package openjoe.smart.sso.base.entity;


public class TokenUser {

    /**
     * User ID
     */
    private Long id;
    /**
     * Username
     */
    private String username;
    /**
     * User roles (role names)
     */
    private java.util.List<String> roles;

    /**
     * Granted permissions for this user in current app
     */
//    private java.util.Set<String> permissions;

    /**
     * Not-granted permissions (to help hide menus/buttons on client)
     */
//    private java.util.Set<String> noPermissions;

    /**
     * Menus associated with granted permissions
     */
//    private java.util.List<TokenMenu> menuList;

    public TokenUser() {
    }

    public TokenUser(Long id, String username) {
        super();
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public java.util.List<String> getRoles() {
        return roles;
    }

    public void setRoles(java.util.List<String> roles) {
        this.roles = roles;
    }
//
//    public java.util.Set<String> getPermissions() {
//        return permissions;
//    }
//
//    public void setPermissions(java.util.Set<String> permissions) {
//        this.permissions = permissions;
//    }
//
//    public java.util.Set<String> getNoPermissions() {
//        return noPermissions;
//    }
//
//    public void setNoPermissions(java.util.Set<String> noPermissions) {
//        this.noPermissions = noPermissions;
//    }

//    public java.util.List<TokenMenu> getMenuList() {
//        return menuList;
//    }
//
//    public void setMenuList(java.util.List<TokenMenu> menuList) {
//        this.menuList = menuList;
//    }
}
