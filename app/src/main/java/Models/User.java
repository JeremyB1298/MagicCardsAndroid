package Models;

import java.util.ArrayList;

public class User {

    private int id;
    private String fbId;
    private String googleId;
    private String name;
    private String email;
    private ArrayList<Card> listCard;
    private Boolean isNew;

    public User(int id, String fbId, String googleId, String name, String email, Boolean isNew) {
        this.id = id;
        this.fbId = fbId;
        this.googleId = googleId;
        this.name = name;
        this.email = email;
        this.isNew = isNew;
    }
    public String getGoogleId(){ return googleId;}

    public String getFbId(){return fbId;}

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

    public void setGoogleId(String googleId){this.googleId = googleId;}

    public void setFbId(String fbId){this.fbId = fbId;}

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
