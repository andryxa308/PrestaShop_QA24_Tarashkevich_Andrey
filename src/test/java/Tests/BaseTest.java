package Tests;

import Pages.*;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.security.AuthProvider;
import java.util.concurrent.TimeUnit;


@Log4j2
@Listeners(InvokedListener.class)
public class BaseTest {
    final static String ITEM_NAME="Printed Dress";
    final static String ITEM_PRICE="31,20 ₴";
    final static String ITEM_DESCRIPTION="100% cotton double printed dress. Black and white striped top and orange high waisted skater skirt bottom.";
    final static String YEAR="1999";
    private final static String URL = PropertyReader.getProperty("base_url");

    protected final static String PASSWORD = PropertyReader.getProperty("password");
    protected String email;
    protected String password ;

    protected WebDriver driver;
    protected AddressesPage addressesPage;
    protected AuthenticationPage authenticationPage;
    protected CreateAnAccountPage createAnAccountPage;
    protected CartPage cartPage;
    protected ItemDetailPage itemDetailPage;
    protected PaymentPage paymentPage;
    protected ProductsPage productsPage;
    protected ShippingPage shippingPage;
    protected HomePage homePage;
    protected final Faker faker=new Faker();

    @BeforeClass(alwaysRun = true, description = "initialise driver")
    public void setUp(ITestContext testContext) throws Exception {
        String browserName = System.getProperty("browser", "chrome");
        driver = DriverFactory.getDriver(browserName);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        testContext.setAttribute("driver", driver);
        addressesPage=new AddressesPage(driver);
        authenticationPage=new AuthenticationPage(driver);
        homePage = new HomePage(driver);
        itemDetailPage=new ItemDetailPage(driver);
        paymentPage=new PaymentPage(driver);
        productsPage=new ProductsPage(driver);
        shippingPage=new ShippingPage(driver);
        cartPage=new CartPage(driver);
        createAnAccountPage=new CreateAnAccountPage(driver);
    }

    @BeforeMethod(alwaysRun = true,description = "navigate and authorisation")
    public void navigateAndCreateAcc() {
        email = faker.internet().emailAddress();
        password = faker.internet().password();

        log.debug("Page opened");
        driver.get("http://prestashop.qatestlab.com.ua/en/");
        homePage.clickToSignInButton();
        authenticationPage.waitForPageLoaded();
        authenticationPage.setEmail(email);
        authenticationPage.clickCreateAccountButton();
        createAnAccountPage.clickTitleButton();
        createAnAccountPage.setFirstName(faker.name().lastName());
        createAnAccountPage.setLastName(faker.name().lastName());
        createAnAccountPage.setPassword(password);
        createAnAccountPage.clickToDayOfBirthSelect(21);
        createAnAccountPage.clickToMonthsOfBirthSelect(5);
        createAnAccountPage.setYearOfBirthInput(YEAR);
        createAnAccountPage.clickSubmitAccount();

    }
    @AfterMethod(alwaysRun = true, description = "clear cookies")
    public void clearCookies() {
        log.debug("Clear all cookies here");
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript(String.format("window.localStorage.clear();"));
        ((JavascriptExecutor) driver).executeScript(String.format("window.sessionStorage.clear();"));
    }
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        log.debug("Driver closed");
        driver.quit();
    }
}
