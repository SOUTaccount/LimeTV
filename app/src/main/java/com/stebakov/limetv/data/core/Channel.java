package com.stebakov.limetv.data.core;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name_ru")
    @Expose
    private String nameRu;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("current")
    @Expose
    private Current current;
    private String favorite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Current getCurrent() { return current; }

    public void setCurrent(Current current) { this.current = current; }

    public void setFavorite(String favorite) { this.favorite = favorite; }

    public String getFavorite() { return favorite; }
}
