package Tests;

import Pages.AuthenticationPage;
import Pages.BasePage;
import Pages.CreateAnAccountPage;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateAccountTest extends BaseTest {
    @Test(groups = {"Smoke"})
    @Description("Positive New Account Authorisation Test")
    @Severity(SeverityLevel.CRITICAL)
    public void positiveCreateAccountTest() {
        Assert.assertTrue(createAnAccountPage.isAccountIconDisplayed());
        Assert.assertEquals(createAnAccountPage.getAccountIconText(),"MY ACCOUNT");
    }

}