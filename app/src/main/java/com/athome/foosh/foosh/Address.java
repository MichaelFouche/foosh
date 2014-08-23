package com.athome.foosh.foosh;

/**
 * Created by Bob on 2014/08/19.
 */
public class Address {

    int id;
    String name;
    String lastname;
    String email;
    String phone_number;
    String address;

    public Address(){

    }

    public Address(int id, String name, String phone_number){
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
    }

    public Address(String name, String lastname, String email, String phone_number, String address){

        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
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
        this.lastname = lastname;
    }

    public String getLastName(){
        return this.lastname;
    }


    public void setLastName(String lastname){
        this.lastname = lastname;
    }

    public String getEmail(){
        return this.email;
    }


    public void setEmail(String email){
        this.email = email;
    }

    public String getAddress(){
        return this.address;
    }


    public void setAddress(String address){
        this.address = address;
    }

    public String getPhoneNumber(){
        return this.phone_number;
    }


    public void setPhoneNumber(String phone_number){
        this.phone_number = phone_number;
    }
}
