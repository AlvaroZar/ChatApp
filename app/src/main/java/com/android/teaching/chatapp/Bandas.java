package com.android.teaching.chatapp;

public class Bandas {
    private String username;
    private String text;

    public Bandas(){super();}

    public Bandas (String username, String text){
        super();
        this.username = username;
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
