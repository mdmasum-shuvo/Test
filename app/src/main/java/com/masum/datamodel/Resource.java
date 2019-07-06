
package com.masum.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resource {

    @SerializedName("event")
    @Expose
    private Event event;
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("uploaded")
    @Expose
    private String uploaded;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUploaded() {
        return uploaded;
    }

    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }

}
