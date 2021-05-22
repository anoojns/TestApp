package com.interview.test;

import android.app.Application;

public class TestingApp extends Application {

    private static TestingApp instance;

    public static TestingApp getInstance() {
        return instance;
    }

    public static void setInstance(TestingApp instance) {
        TestingApp.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
    }
}
