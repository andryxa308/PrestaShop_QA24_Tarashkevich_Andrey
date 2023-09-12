package Pages;

import Utils.AllureUtils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@Log4j2
public class ShippingPage extends BasePage{

    public ShippingPage(WebDriver driver) {

        super(driver);
    }
    private  By agreeCheckBoxOnShippingPage = By.cssSelector("#cgv");
    private  By proceedToCheckoutButtonOnShippingPage = By.xpath("//button[@name='processCarrier']");
    private  By shippingPageHeader =By.cssSelector(".page-heading");
    private  By errorMessage = By.cssSelector(".fancybox-error");
    private  By closeErrorMessageButton= By.xpath("//a[@title='Close']");
    @Override
    public void waitForPageLoaded() {
        log.info("Wait for shipping page loaded");
        waitForElementDisplayed(shippingPageHeader);
    }
    public boolean isShippingHeaderDisplayed(){
        return driver.findElement(shippingPageHeader).isDisplayed();
    }
    public boolean isErrorMessageDisplayed(){
        AllureUtils.attachScreenshot(driver);
        return driver.findElement(errorMessage).isDisplayed();
    }
    public String getErrorMassageText() {
        log.info("Get text from error massage on shipping page");
        return driver.findElement(errorMessage).getText();
    }
    public void clickCloseErrorMessageButton(){
        log.info("Click 'close' button");
        driver.findElement(closeErrorMessageButton).click();
    }
    public void clickAgreeCheckBoxOnShippingPageButton(){
        log.info("Click 'Agree' check box button");
        driver.findElement(agreeCheckBoxOnShippingPage).click();
    }
    public void clickProceedToCheckoutButtonOnShippingPageButton(){
        log.info("Click 'Proceed To Checkout' button");
        driver.findElement(proceedToCheckoutButtonOnShippingPage).click();
    }

}
