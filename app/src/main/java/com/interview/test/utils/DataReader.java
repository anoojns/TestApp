package com.interview.test.utils;

import com.interview.test.TestingApp;

import java.io.IOException;
import java.io.InputStream;

public class DataReader {

    public String readData(String fileName) {

        String contentString = "";

        try {
            InputStream stream = TestingApp.getInstance().getAssets().open(fileName);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            contentString = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentString;

    }
}
