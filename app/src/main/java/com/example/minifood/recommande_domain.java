package com.example.minifood;

import java.io.Serializable;

public class recommande_domain implements Serializable {
    private String title;
    private String pic;
    private String description;
    private double fee;
    private int numb;

    public recommande_domain(String title, String pic, String description, double fee) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;

    }

    public recommande_domain(String title, String pic, String description, double fee, int numb) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.numb = numb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }
}
