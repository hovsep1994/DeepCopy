package com.deepcopy.models;

public class NoConstructor {

    private String name;

    public NoConstructor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NoConstructor{" + "name=" + name + '}';
    }
}
