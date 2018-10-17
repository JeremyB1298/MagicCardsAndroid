package com.example.lpiem.magiccards;

import java.util.ArrayList;

public class User {

    private int id;
    private String name;
    private String email;
    private ArrayList<Card> listCard;
    private Boolean isNew;

    public User(int id, String name, String email, ArrayList<Card> listCard, Boolean isNew) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.listCard = listCard;
        this.isNew = isNew;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Card> getListCard() {
        return listCard;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setListCard(ArrayList<Card> listCard) {
        this.listCard = listCard;
    }
}
