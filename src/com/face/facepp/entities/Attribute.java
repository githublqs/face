package com.face.facepp.entities;


   
public class Attribute {

   private Age age;
   private Gender gender;
   private Glass glass;
   private Pose pose;
   private Race race;
   private Smiling smiling;


    public void setAge(Age age) {
        this.age = age;
    }
    public Age getAge() {
        return age;
    }
    

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public Gender getGender() {
        return gender;
    }
    

    public void setGlass(Glass glass) {
        this.glass = glass;
    }
    public Glass getGlass() {
        return glass;
    }
    

    public void setPose(Pose pose) {
        this.pose = pose;
    }
    public Pose getPose() {
        return pose;
    }
    

    public void setRace(Race race) {
        this.race = race;
    }
    public Race getRace() {
        return race;
    }
    

    public void setSmiling(Smiling smiling) {
        this.smiling = smiling;
    }
    public Smiling getSmiling() {
        return smiling;
    }
    
}