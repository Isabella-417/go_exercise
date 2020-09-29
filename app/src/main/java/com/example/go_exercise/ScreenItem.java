package com.example.go_exercise;

public class ScreenItem {
    private String title;
    private String description;
    private int screenimg;


    public ScreenItem(String title, String description, int screenimg){
        this.setTitle(title);
        this.setDescription(description);
        this.setScreenimg(screenimg);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScreenimg() {
        return screenimg;
    }

    public void setScreenimg(int screenimg) {
        this.screenimg = screenimg;
    }
}
