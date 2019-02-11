package com.saber.entity;

import javax.xml.bind.annotation.XmlElement;

public class User {

    private String name;

    private String age;

    private String address;

    public User(String name, String age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @XmlElement
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
