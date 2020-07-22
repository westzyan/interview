package com.zyan.singleton;

public enum  Singleton {
    INSTANCE;
    private User instance;
    Singleton() {
        instance = new User();
    }
    public User getInstance() {
        return instance;
    }
}

class User{
    public static void main(String[] args) {
        User instance = Singleton.INSTANCE.getInstance();
    }
}