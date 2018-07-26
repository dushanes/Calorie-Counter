package dushanes.caloriecounterub;

import java.io.Serializable;

class user implements Serializable{

    public user (){}

    public user(int i, String e){
        this.id = i;
        this.email = e;
    };

    public String name;

    public String email;

    private int id;

    public int calories;

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(int maintCalories) {
        this.calories = maintCalories;
    }

    public int getCalories() {
        return calories;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

}
