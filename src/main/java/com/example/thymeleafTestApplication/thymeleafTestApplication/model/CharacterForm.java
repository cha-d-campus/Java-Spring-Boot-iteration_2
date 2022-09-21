package com.example.thymeleafTestApplication.thymeleafTestApplication.model;

public class CharacterForm {
    private int id;
    private String name;
    private String type;
    private int lifepoints;

    private int maxlifepoints;

    private String image;

    public CharacterForm() {
    }

    public CharacterForm(int id, String name, String type, int lifepoints, String image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.lifepoints = lifepoints;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLifepoints() {
        return lifepoints;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLifepoints(int lifepoints) {
        this.lifepoints = lifepoints;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
