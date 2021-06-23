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
public class ActivityDto {

    private int id;
    private int userId;
    private String activity;
    private String date;
    private int byUserId;
    
    private String name;
    private String email;

    public ActivityDto() {
    }

    public ActivityDto(int userId, String activity,  int byUserId, String name, String email) {
        this.userId = userId;
        this.activity = activity;
        this.byUserId = byUserId;
        this.name = name;
        this.email = email;
    }


    public ActivityDto(int userId, String activity, int byUserId) {
        this.userId = userId;
        this.activity = activity;
        this.byUserId = byUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getByUserId() {
        return byUserId;
    }

    public void setByUserId(int byUserId) {
        this.byUserId = byUserId;
    }

}
