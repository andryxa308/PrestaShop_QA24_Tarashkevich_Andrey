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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {
    final static String FIRST_NAME = "Andrey";
    final static String LAST_NAME = "Tarashkevich";
    @Test(groups = {"Smoke"})
    @Description("Positive Sign In Test")
    @Severity(SeverityLevel.CRITICAL)
    public void positiveSignInTest() {
        homePage.clickToSignOutButton();
        authenticationPage.authentication(email, password);
        Assert.assertTrue(createAnAccountPage.isAccountIconDisplayed());
        Assert.assertEquals(createAnAccountPage.getAccountIconText(), "AUTHENTICATION");
    }

    @Test(dataProvider = "negativeCreateAccount", groups = {"Negative"})
    @Description("Troubles with first name, last name and password when you try to create new account")
    @Severity(SeverityLevel.CRITICAL)
    public void negativeCreateAccountTest(String email, String firstName, String lastName, String password, String errorMessage) {
        homePage.clickToSignOutButton();
        authenticationPage.setEmail(email);
        authenticationPage.clickCreateAccountButton();
        createAnAccountPage.clickTitleButton();
        createAnAccountPage.setFirstName(firstName);
        createAnAccountPage.setLastName(lastName);
        createAnAccountPage.setPassword(password);
        createAnAccountPage.clickSubmitAccount();
        Assert.assertTrue(createAnAccountPage.isErrorMessageDisplayed());
        Assert.assertEquals(createAnAccountPage.getErrorMessageText(), errorMessage);
    }

    @DataProvider(name = "negativeCreateAccount")
    public Object[][] negativeCreateAccountTestData() {
        return new Object[][]{
                {"zxklopc@mail.ru", FIRST_NAME, LAST_NAME, "", "passwd is required."},
                {"lokijklh@gmail.com", FIRST_NAME, "", PASSWORD, "lastname is required."},
                {"qwedfbvb@mail.ru", "", LAST_NAME, PASSWORD, "firstname is required."},
        };
    }
}