package com.interview.test.model;

import com.google.gson.annotations.SerializedName;

public class Poster {


    /**
     * name : The Birds
     * poster-image : poster1.jpg
     */

    private String name;
    @SerializedName("poster-image")
    private String posterimage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterimage() {
        return posterimage;
    }

    public void setPosterimage(String posterimage) {
        this.posterimage = posterimage;
    }

    public Poster(String name, String posterimage) {
        this.name = name;
        this.posterimage = posterimage;
    }
}
