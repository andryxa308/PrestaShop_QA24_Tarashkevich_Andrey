package Tests;

import Pages.BasePage;
import Pages.HomePage;
import Pages.ItemDetailPage;
import Pages.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchTests extends BaseTest{
    final static String ITEM="Pasta";
    @Test(groups = {"Regression"})
    @Description("Search dresses and how many dresses are in prestashop")
    @Severity(SeverityLevel.NORMAL)
    public void positiveSearchDressesTest() {
        homePage.setProductNameToSearchInput(ITEM_NAME);
        homePage.clickSearchButton();
        Assert.assertTrue(productsPage.isResultsMassageDisplayed());
        Assert.assertEquals(productsPage.getResultsMassageText(),"13 results have been found.");
    }
    @Test(groups = {"Negative"})
    @Description("Search not exist item")
    @Severity(SeverityLevel.TRIVIAL)
    public void negativeSearchItemTest() {
        homePage.setProductNameToSearchInput(ITEM);
        homePage.clickSearchButton();
        Assert.assertTrue(productsPage.isWarningAlertDisplayed());
        Assert.assertEquals(productsPage.getWarningAlertText(),"No results were found for your search \"Pasta\"");
        Assert.assertTrue(productsPage.isResultsMassageDisplayed());
        Assert.assertEquals(productsPage.getResultsMassageText(),"0 results have been found.");
    }
    @Test(groups = {"Regression"})
    @Description("Search product on home page")
    @Severity(SeverityLevel.NORMAL)
    public void searchProductTest() {
        homePage.setProductNameToSearchInput(ITEM_NAME);
        homePage.clickSearchButton();
        Assert.assertTrue(productsPage.isResultsMassageDisplayed());
        Assert.assertEquals(productsPage.getResultsMassageText(), "13 results have been found.");
    }

}

