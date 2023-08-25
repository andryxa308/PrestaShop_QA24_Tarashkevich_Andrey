package Pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public abstract class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }
    private  By casualDresses = By.xpath("//div[@id='categories_block_left']/descendant::a[contains(text(), 'Casual Dresses')]");
    private  By eveningDresses = By.xpath("//div[@id='categories_block_left']/descendant::a[contains(text(), 'Evening Dresses')]");
    private  By summerDresses = By.xpath("//div[@id='categories_block_left']/descendant::a[contains(text(), 'Summer Dresses')]");
    private  By dressPageIcon = By.cssSelector(".cat-name");
    private  By resultsMessage = By.xpath("//span[@class='heading-counter']");
    private  By productName= By.cssSelector("#center_column .product-name");
    private  By productPrice = By.cssSelector("#center_column .right-block [itemprop='price']");
private  By itemLink= By.xpath("//div[@class='right-block']//a[@class='product-name']");
    private  By productLink =By.cssSelector("a[class$=_link]");
    private final String productContainerLocator
            = "//*[@class='product_img_link' and @title='%s']/ancestor::div[@class='product-container']";
    private final String productContainerPriceLocator
            = "//span[@class='price product-price' and contains(text(),'%s ')] /ancestor::div[@class='product-container']";

    private final  By warningAlert = By.xpath("//p[@class='alert alert-warning']");

    @Override
    public void waitForPageLoaded() {
        log.info("Wait for dress page loaded");
        waitForElementDisplayed(dressPageIcon);
    }

    public void clickCasualDressesButton() {
        log.info("Click 'Casual Dresses' button");
        driver.findElement(casualDresses).click();
    }

    public void clickEveningDressesButton() {
        log.info("Click 'Evening Dresses' button");
        driver.findElement(eveningDresses).click();
    }

    public void clickSummerDressesButton() {
        log.info("Click 'Summer Dresses' button");
        driver.findElement(summerDresses).click();
    }

    public void openItemByName(String productsName) {
        log.info(String.format("Open Item with product name = %s ",productsName));
        WebElement productContainer = getProductContainerByName(productsName);
        productContainer.findElement(itemLink).click();
    }

    private WebElement getProductContainerByName(String productsName) {
        return driver.findElement(
                By.xpath(
                        String.format(productContainerLocator, productsName)
                )
        );
    }
    public boolean getProductName(String productsName) {
        WebElement productContainer = getProductContainerByName(productsName);
        return productContainer.findElement(productName).isDisplayed();
    }

    public boolean isResultsMassageDisplayed(){
        return driver.findElement(resultsMessage).isDisplayed();
    }
    public String getResultsMassageText() {
        log.info("Get text massage after searching product by name");
        return driver.findElement(resultsMessage).getText();
    }
    private WebElement getProductContainerByPrice(String productsPrice) {
        return driver.findElement(
                By.xpath(
                        String.format(productContainerPriceLocator, productsPrice)));
    }
    public boolean getProductPrice(String productsPrice) {
        WebElement productContainer = getProductContainerByPrice(productsPrice);
        return productContainer.findElement(productPrice).isDisplayed();
    }

    public boolean isWarningAlertDisplayed(){
        return driver.findElement(warningAlert).isDisplayed();
    }
    public String getWarningAlertText(){
        log.info("Get text from warning alert after searching product by name");
        return driver.findElement(warningAlert).getText();
    }

    public abstract void open();

    public abstract BasePage isPageOpened();
}
