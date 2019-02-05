package com.upgrade.UpgradeAPITesting.utils;

import org.json.simple.JSONObject;
import java.util.ArrayList;



public class FindElementInJson {

    public static Object getElement(ArrayList<JSONObject> list, String keyToFind) {
        for (JSONObject jsonObject : list) {
            if (jsonObject.get("key").equals(keyToFind)) {
                return jsonObject.get("value");
            }
        }
        return null;
    }
}
