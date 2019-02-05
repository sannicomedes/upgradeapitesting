package com.upgrade.UpgradeAPITesting.Pages;

import com.upgrade.UpgradeAPITesting.utils.FindElementInJson;
import com.upgrade.UpgradeAPITesting.utils.Logger;
import com.upgrade.UpgradeAPITesting.utils.ReadJson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class EligibleStatesPage extends PageBase {
    public EligibleStatesPage(RequestSpecification request, Response response) {
        super(request, response);
    }

    public Response getStatesList() {
        String url = (String) FindElementInJson.getElement((ArrayList<JSONObject>) ReadJson.getJson(new File("jsons\\Url.json")).get("values"), "url");
        baseURI = url;
        request = given();
        response = request.get();
        return response;
    }

    public boolean isSchemaValid(Response response) {
        boolean valid = false;

        try {
            response.then().body(matchesJsonSchemaInClasspath("statesListSchema.json"));
            valid = true;
            Logger.printInfo("States List is valid");
        } catch (Exception e) {
            Logger.printError("States List is invalid due to: ".concat(e.getMessage()));
        }
        return valid;
    }
    public boolean areAllStateNamesValid(Response response){
        boolean valid = true;
        ArrayList<JSONObject> json = response.jsonPath().getJsonObject("states");

        for (HashMap<String, String> jsonObject: json)
           if (findStateInJson(jsonObject.get("label").toString()))
              Logger.printInfo(jsonObject.get("label").toString() + "State is Valid");
           else {
               Logger.printInfo(jsonObject.get("label").toString() + "State is NOT Valid. Thus, State List is invalid");
               valid = false;
               break;
           }
        return valid;
    }
    public boolean isTotalStateCount48(Response response){
        boolean count = false;
        List<JSONObject> json = response.jsonPath().getJsonObject("states");

        if (json.size()==48)
            count= true;
        Logger.printInfo(json.size() + " is the Total of names in the States List");
        return count;
    }
    public boolean onlyOneStateWithMinAge(Response response){
        boolean min = false;
        int count = 0;
        ArrayList<JSONObject> json = response.jsonPath().getJsonObject("states");

        for (HashMap<String, Object> jsonObject: json)
            if (((Integer)jsonObject.get("minAge"))==19) {
                count++;
                Logger.printInfo(jsonObject.get("label").toString() + " has " + ((Integer) jsonObject.get("minAge")).intValue() + " as a minimun age");
            }
        if(count==1)
            min = true;
        return min;
    }
    public boolean onlyStateWithMinLoan(Response response){
        boolean min = false;
        int count = 0;
        ArrayList<JSONObject> json = response.jsonPath().getJsonObject("states");
        List<String> states = new ArrayList<>();

        for (HashMap<String, Object> jsonObject: json)
            if ((((Float)jsonObject.get("minLoanAmount"))==3005)) {
                count++;
                states.add(jsonObject.get("label").toString());
            }
        if((count==1))
            min = true;
        for(String state: states)
           Logger.printInfo(state + " has $3005 as a minimun loan requirement");
        return min;
    }
}
