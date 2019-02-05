package com.upgrade.UpgradeAPITesting.tests;

import com.upgrade.UpgradeAPITesting.Pages.EligibleStatesPage;
import com.upgrade.UpgradeAPITesting.commons.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyAllStateNamesAreValidTest extends TestBase {
    EligibleStatesPage list = new EligibleStatesPage(request, response);
    @Test(priority = 0, description = "Get States List")
    public void getStatesList(){
        response = list.getStatesList();
    }
    @Test(priority = 1, description = "Status Code")
    public void checkStatusCode(){
        Assert.assertTrue(list.isStatusCode(response));
    }
    @Test(priority = 2, description = "Response Validation against Schema")
    public void checkSchema(){
        Assert.assertTrue(list.isSchemaValid(response));
    }
    @Test(priority = 3, description = "State Names Validation")
    public void checkAllStateNamesAreValid()
    {
        Assert.assertTrue(list.areAllStateNamesValid(response));
    }
    @Test(priority = 4, description = "States Count")
    public void checkTotalStateCount()
    {
        Assert.assertTrue(list.isTotalStateCount48(response));
    }
}
