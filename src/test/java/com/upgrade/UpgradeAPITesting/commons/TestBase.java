package com.upgrade.UpgradeAPITesting.commons;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
    protected RequestSpecification request;
    protected Response response;
}
