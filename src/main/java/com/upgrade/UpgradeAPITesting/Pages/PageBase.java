package com.upgrade.UpgradeAPITesting.Pages;

import com.upgrade.UpgradeAPITesting.utils.Logger;
import com.upgrade.UpgradeAPITesting.utils.ReadJson;
import com.upgrade.UpgradeAPITesting.utils.WriteJson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PageBase {
    protected Object value;
    protected RequestSpecification request;
    protected Response response;
    protected JSONArray array = null;

    public PageBase(RequestSpecification request, Response response){
        this.request = request;
        this.response = response;
    }
    public boolean isStatusCode(Response response) {
        boolean status = false;
        int code = response.getStatusCode();

        if (code == 200) {
           status = true;
           Logger.printInfo("Status Code is " + code);
        }else
             Logger.printError("Status code is not 200, it was " + code + ". This is the error message: " + response.getBody().prettyPrint());
        return status;
    }
    public boolean isResponseTimeLessThan(Response response, long time) {
        boolean less = false;
        long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);

        if (responseTime<time) {
            less = true;
            Logger.printInfo("Response Time is less than " + time + "ms. This was the Response Time: " + responseTime + "ms");
        } else
            Logger.printError("Response Time is greater than " + time + "ms. This was the Response Time: " + responseTime + "ms");
        return less;
    }
    public boolean isSucessful(Response response){
        boolean success = response.jsonPath().getBoolean("Success");

        Logger.printInfo("Sucess is: ".concat(String.valueOf(success)).concat(response.jsonPath().prettyPrint()));

        return success;
    }
    public boolean findStateInJson(String stateInResponse){
        JSONObject json = ReadJson.getJson(new File("jsons\\states.json"));
        boolean found = false;

        for (JSONObject jsonObject: (ArrayList<JSONObject>) json.get("states")){
            if (jsonObject.get("name").equals(stateInResponse)){
                found = true;
                break;
            }
        }
        return found;
    }
}

