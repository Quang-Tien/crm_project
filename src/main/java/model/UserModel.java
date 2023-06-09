package model;

public class UserModel {
    private int id;
    private String email;
    private String password;
    private String fullname;
    private String avatar;
    private int roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRoleId() {
        return roleId;
    }

//    public String getRoleName() {
//        switch (roleId) {
//            case 1:
//                return "Admin";
//            case 2:
//                return "Manager";
//
//            case 3:
//                return "Employee";
//
//            default:
//                return "Unknown";
//
//        }
//
//    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
