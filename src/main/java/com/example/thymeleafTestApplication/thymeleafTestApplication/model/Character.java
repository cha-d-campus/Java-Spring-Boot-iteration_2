package com.example.thymeleafTestApplication.thymeleafTestApplication.model;

public class Character {
    private int id;
    private String name;
    private String type;
    private int lifepoints;

    public Character() {
    }

    public Character(int id, String name, String type, int lifepoints) {
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

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", nom='" + name + '\'' +
                ", type='" + type + '\'' +
                ", points de vie" + lifepoints +
                '}';
    }
}