package Tests;


import io.qameta.allure.Description;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;

import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    final static String FIRST_NAME_FOR_ADDRESS_PAGE="Andrey";
    final static String LAST_NAME_FOR_ADDRESS_PAGE="Tarashkevich";
    final static String ADDRESS_FOR_ADDRESS_PAGE="street New home 1 flat 1";
    final static String ZIP_POSTAL_CODE_FOR_ADDRESS_PAGE="10088";
    final static String CITY_FOR_ADDRESS_PAGE="New city";
    final static String HOME_PHONE_FOR_ADDRESS_PAGE="11234567890";
    final static String ADDRESS_TITLE_FOR_ADDRESS_PAGE="First address";

    @Test(groups = {"Smoke"})
    @Description("Add product to cart")
    @Severity(SeverityLevel.CRITICAL)
    public void addItemToCartTest() {
        homePage.clickToDressesSectionButton();
        productsPage.waitForPageLoaded();
        productsPage.clickCasualDressesButton();
        productsPage.openItemByName(ITEM_NAME);
        itemDetailPage.clickAddToCardButton();
        itemDetailPage.waitForAddToCartItemIconDisplayed();
        Assert.assertTrue(itemDetailPage.isAddToCartItemIconDisplayed());
        Assert.assertEquals(itemDetailPage.getAddToCartItemIconText(),"Товар был успешно добавлен в вашу корзину");
    }
    @Test(groups = {"Smoke"})
    @Description("Remove product from cart")
    @Severity(SeverityLevel.CRITICAL)
    public void removeItemFromCartTest() {
        homePage.clickToDressesSectionButton();
        productsPage.waitForPageLoaded();
        productsPage.clickCasualDressesButton();
        productsPage.openItemByName(ITEM_NAME);
        itemDetailPage.clickAddToCardButton();
        itemDetailPage.waitForAddToCartItemIconDisplayed();
        Assert.assertTrue(itemDetailPage.isAddToCartItemIconDisplayed());
        Assert.assertEquals(itemDetailPage.getAddToCartItemIconText(),"Product successfully added to your shopping cart");
        itemDetailPage.clickCloseWindowButton();
        itemDetailPage.clickCartButtonItemDetailsButton();
        cartPage.waitForPageLoaded();
        cartPage.clickTrashButton();
        cartPage.clickCartButton();
        Assert.assertTrue(cartPage.isAlertDisplayed());
    }
    @Test(groups = {"Regression"})
    @Description("Actions on addresses page")
    @Severity(SeverityLevel.CRITICAL)
    public void actionsOnAddressesPageTestWithSelect() {
        homePage.clickToDressesSectionButton();
        productsPage.waitForPageLoaded();
        productsPage.clickCasualDressesButton();
        productsPage.openItemByName(ITEM_NAME);
        Assert.assertEquals(itemDetailPage.getItemName(),ITEM_NAME);
        Assert.assertEquals(itemDetailPage.getItemShortDescription(), ITEM_DESCRIPTION);
        itemDetailPage.clickAddToCardButton();
        itemDetailPage.clickCloseWindowButton();
        itemDetailPage.clickCartButtonItemDetailsButton();
        cartPage.waitForPageLoaded();
        cartPage.clickProceedToCheckoutButton();
        addressesPage.waitForPageLoaded();
        addressesPage.setFirstNameAddressPage(FIRST_NAME_FOR_ADDRESS_PAGE);
        addressesPage.setLastNameAddressPage(LAST_NAME_FOR_ADDRESS_PAGE);
        addressesPage.setAddress(ADDRESS_FOR_ADDRESS_PAGE);
        addressesPage.setZipPostalCode(ZIP_POSTAL_CODE_FOR_ADDRESS_PAGE);
        addressesPage.setCity(CITY_FOR_ADDRESS_PAGE);
        addressesPage.setHomePhone(HOME_PHONE_FOR_ADDRESS_PAGE);
        addressesPage.chooseStateWithSelect(5);
        addressesPage.setAddressTitle(ADDRESS_TITLE_FOR_ADDRESS_PAGE);
        addressesPage.clickSaveAddressButton();
        Assert.assertTrue(addressesPage.isAddressCompleteIconDisplayed());
        homePage.clickToAccountButton();
        myAccountPage.clickToMyAddressesButton();
        myAccountPage.clickToDeleteAddressesButton();
        myAccountPage.clickToAlert();
    }
}