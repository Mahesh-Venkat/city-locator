package com.backbase.citylocator.utils;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CityLocatorUtils {

    public static String readRawTextFile(Activity activity, String fileName) {

        StringBuilder resultedString = new StringBuilder();

        try {
            InputStream json = activity.getAssets().open(fileName);
            BufferedReader inputReader =
                    new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String string;

            while ((string = inputReader.readLine()) != null) {
                resultedString.append(string);
            }

            inputReader.close();
        } catch (IOException e) {
            return null;
        }

        return resultedString.toString();
    }
}
