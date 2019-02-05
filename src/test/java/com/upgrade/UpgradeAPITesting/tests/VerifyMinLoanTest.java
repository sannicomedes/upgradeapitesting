package com.upgrade.UpgradeAPITesting.tests;

import com.upgrade.UpgradeAPITesting.Pages.EligibleStatesPage;
import com.upgrade.UpgradeAPITesting.commons.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyMinLoanTest extends TestBase {
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
    @Test(priority = 3, description = "Minimun Loan Requirement as $3005")
    public void checkMinLoan()
    {
        Assert.assertTrue(list.onlyStateWithMinLoan(response));
    }
}
