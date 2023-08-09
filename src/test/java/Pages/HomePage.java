package Pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@Log4j2
public class HomePage  extends BasePage{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForPageLoaded() {
        log.info("Waiting for home page loaded");
        waitForElementDisplayed(home_Picture);
    }



    protected  By home_Picture= By.xpath("//img[@src='http://prestashop.qatestlab.com.ua/modules/homeslider/images/sample-1.jpg']");
    private  By signInHomePageButton = By.cssSelector(".login");
    private  By cartButton = By.cssSelector(".shopping_cart");
    private  By searchInput = By.cssSelector("#search_query_top");
    private  By searchButton = By.xpath("//button[@name='submit_search']");
    private  By womenSection = By.xpath("//a[@title='Women']");
    private  By dressesSection = By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/descendant::a[@title='Dresses'][2]");
    private  By tShirtSection = By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/descendant::a[@title='T-shirts'][2]");
    private  By headerLogo = By.cssSelector("#header_logo");
    private  By signOutHomePageButton = By.cssSelector(".logout");
    private   By item =By.xpath( "//h5//a[@title=' + itemName + ']");
    private  By accountButton = By.cssSelector(".account");

    public void clickToSignInButton(){
        log.info("Click 'Sign in' button");
        driver.findElement(signInHomePageButton).click();
    }
    public void clickToSignOutButton(){
        log.info("Click 'Sign Out' button");
        driver.findElement(signOutHomePageButton).click();
    }
    public void setProductNameToSearchInput(String productName){
        log.info(String.format("Set product name = %s", productName));
        driver.findElement(searchInput).sendKeys(productName);
    }
    public void clickSearchButton(){
        log.info("Click 'Search' button");
        driver.findElement(searchButton).click();
    }
    public void clickLogoButton(){
        log.info("Click 'Logo' button");
        driver.findElement(headerLogo).click();
    }

    public void clickToCartButton(){
        driver.findElement(cartButton).click();
    }

    public void clickToDressesSectionButton(){
        log.info("Click 'Dresses Section' button");
        driver.findElement(dressesSection).click();
    }
    public void clickToItemName(){
        log.info("Click 'item name' icon");
        driver.findElement(item).click();
    }
    public void clickToAccountButton(){
        log.info("Click account button on addresses page");
        driver.findElement(accountButton).click();
    }

    public void clickToWomenSectionButton(){
        driver.findElement(womenSection).click();
    }
    public void clickToTshirtButton(){
        driver.findElement(tShirtSection).click();
    }
}
