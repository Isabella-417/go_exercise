package com.example.go_exercise.utilidades;

public class ScreenItem {
    private String title;
    private String description;
    private String screenimg;


    public ScreenItem(String title, String description, String screenimg){
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

    public String getScreenimg() {
        return screenimg;
    }

    public void setScreenimg(String screenimg) {
        this.screenimg = screenimg;
    }
}
