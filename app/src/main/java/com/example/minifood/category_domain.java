package com.example.minifood;

public class category_domain {
    private String title;
    private String pic;

    public category_domain(String title, String pic) {
        this.title = title;
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
