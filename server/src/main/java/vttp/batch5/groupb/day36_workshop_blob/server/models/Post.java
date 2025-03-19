package vttp.batch5.groupb.day36_workshop_blob.server.models;

import java.io.InputStream;
import java.util.Base64;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Post {
    private String postId;
    private String comments;
    private InputStream picture;
    private byte[] pictureBlob;

    public byte[] getPictureBlob() {
        return pictureBlob;
    }
    public void setPictureBlob(byte[] pictureBlob) {
        this.pictureBlob = pictureBlob;
    }
    public String getPostId() {
        return postId;
    }
    public void setPostId(String postId) {
        this.postId = postId;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public InputStream getPicture() {
        return picture;
    }
    public void setPicture(InputStream picture) {
        this.picture = picture;
    }

    public JsonObject toJson() {
        JsonObjectBuilder ob = Json.createObjectBuilder();
        ob.add("postId", postId);
        ob.add("comments", comments);
        ob.add("pictureBlob", Base64.getEncoder().encodeToString(pictureBlob));
        return ob.build();
    }
}
