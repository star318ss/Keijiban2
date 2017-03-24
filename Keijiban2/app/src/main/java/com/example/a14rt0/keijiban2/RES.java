package com.example.a14rt0.keijiban2;

/**
 * Created by 14rt0 on 2017/03/24.
 */

import io.realm.RealmObject;

// Define you model class by extending RealmObject
public class RES extends RealmObject {
    private String resnum;
    private String time;
    private String name;
    private String comment;

    // ... Generated getters and setters ...
    public void setResnum(String resnum) {
        this.resnum = resnum;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getResnum() {
        return resnum;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

}
