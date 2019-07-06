package com.masum.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

@SerializedName("action")
@Expose
private String action;
@SerializedName("message")
@Expose
private String message;
@SerializedName("reason")
@Expose
private String reason;
@SerializedName("userid")
@Expose
private Integer userid;

public String getAction() {
return action;
}

public void setAction(String action) {
this.action = action;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public String getReason() {
return reason;
}

public void setReason(String reason) {
this.reason = reason;
}

public Integer getUserid() {
return userid;
}

public void setUserid(Integer userid) {
this.userid = userid;
}

}