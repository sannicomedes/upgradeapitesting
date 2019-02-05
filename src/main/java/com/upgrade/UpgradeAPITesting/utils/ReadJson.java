package com.upgrade.UpgradeAPITesting.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadJson {
    private static JSONObject jsonObject = new JSONObject();

    public static JSONObject getJson(File jsonFile) {

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(jsonFile));
            jsonObject = (JSONObject) obj;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}