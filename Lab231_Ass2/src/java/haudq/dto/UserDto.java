/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haudq.dto;

/**
 *
 * @author haudq
 */
public class UserDto {
    private int id;
    private String userId;
    private String password;
    private String name;
    private String role;
    private String email;
    private String phone;
    private String avatar;
    private String status;
    
    private int rank;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public UserDto() {
    }

    public UserDto(int id, String userId, String name, String role, String email, String phone, String avatar, String status, int rank) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.status = status;
        this.rank = rank;
    }

    
    public UserDto(String userId, String password, String name, String role, String email, String phone, String avatar, String status) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.status = status;
    }

    
    public UserDto(int id, String userId, String password, String name, String role, String email, String phone, String avatar, String status) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.status = status;
    }

    public UserDto(int id, String userId, String password, String name, String role, String email, String phone, String avatar) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
    }

    
    

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

     
    
}
