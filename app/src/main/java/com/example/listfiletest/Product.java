package com.example.listfiletest;

public class Product {

    private String name ;
    private String description ;
    private int id ;



    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId(){
        return id ;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product(int id ,String name, String description) {
        this.name = name;
        this.description = description;
        this.id=id ;
    }

    public Product() { }
}
