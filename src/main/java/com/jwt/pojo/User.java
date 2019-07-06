package com.jwt.pojo;

public class User {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private String city;
    private String address;
    private String gsmNumber;

    public User(int id, String username, String password, String firstName, String lastName, int age, String city,
                String address, String gsmNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.address = address;
        this.gsmNumber = gsmNumber;
    }

    public User(String username, String password, String firstName, String lastName, int age, String city, String address, String gsmNumber) {
        this(0, username, password, firstName, lastName, age, city, address, gsmNumber);
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGsmNumber() {
        return gsmNumber;
    }

    public void setGsmNumber(String gsmNumber) {
        this.gsmNumber = gsmNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
