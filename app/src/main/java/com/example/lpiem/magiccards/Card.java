package com.example.lpiem.magiccards;

public class Card {

    private int id;
    private String multiverseid;
    private String name;
    private String rarity;

    public Card(int id, String multiverseid, String name, String rarity) {
        this.id = id;
        this.multiverseid = multiverseid;
        this.name = name;
        this.rarity = rarity;
    }

    public int getId() {
        return id;
    }

    public String getMultiverseid() {
        return multiverseid;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setMultiverseid(String multiverseid) {
        this.multiverseid = multiverseid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}
