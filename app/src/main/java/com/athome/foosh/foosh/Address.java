package com.athome.foosh.foosh;

/**
 * Created by Bob on 2014/08/19.
 */
public class Address {

    int id;
    String name;
    String phone_number;

    public Address(){

    }

    public Address(int id, String name, String phone_number){
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
    }

    public Address(String name, String phone_number){
        this.name = name;
        this.phone_number = phone_number;
    }

    public int getID(){
        return this.id;
    }


    public void setID(int id){
        this.id = id;
    }


    public String getName(){
        return this.name;
    }


    public void setName(String name){
        this.name = name;
    }


    public String getPhoneNumber(){
        return this.phone_number;
    }


    public void setPhoneNumber(String phone_number){
        this.phone_number = phone_number;
    }
}
