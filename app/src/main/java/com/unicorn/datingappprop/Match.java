package com.unicorn.datingappprop;

public class Match {
    private String matchName;
    private int matchAge;
    private String matchProfile;
    private int matchImage;

    public Match(String name, int age, String profile, int image){
        this.matchName = name;
        this.matchAge = age;
        this.matchProfile = profile;
        this.matchImage = image;
    }

    public void setName(String name){
        this.matchName = name;
    }

    public void setAge(int age){
        this.matchAge = age;
    }

    public void setProfile(String profile){
        this.matchProfile = profile;
    }

    public void setImage(int image){
        this.matchImage = image;
    }

    public String getName(){
        return matchName;
    }

    public int getAge(){
        return matchAge;
    }

    public String getProfile(){
        return matchProfile;
    }

    public int getImage(){
        return matchImage;
    }
}
