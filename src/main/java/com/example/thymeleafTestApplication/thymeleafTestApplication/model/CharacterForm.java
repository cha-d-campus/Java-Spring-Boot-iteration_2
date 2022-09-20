package com.example.thymeleafTestApplication.thymeleafTestApplication.model;

public class CharacterForm {
    private int id;
    private String name;
    private String type;
    private int lifepoints;

    public CharacterForm() {
    }

    public CharacterForm(int id, String name, String type, int lifepoints) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.lifepoints = lifepoints;
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
}
