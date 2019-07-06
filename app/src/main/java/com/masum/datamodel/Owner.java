
package com.masum.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("primary_team")
    @Expose
    private String primaryTeam;
    @SerializedName("avatar")
    @Expose
    private String avatar;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrimaryTeam() {
        return primaryTeam;
    }

    public void setPrimaryTeam(String primaryTeam) {
        this.primaryTeam = primaryTeam;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
