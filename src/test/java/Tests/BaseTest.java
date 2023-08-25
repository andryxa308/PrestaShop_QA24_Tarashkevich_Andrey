package Tests;

import Pages.*;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.time.Duration;
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
    protected String password;


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
    protected MyAccountPage myAccountPage;
    protected final Faker faker=new Faker();


    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserName, ITestContext context) throws Exception {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        context.setAttribute("driver", driver);
        addressesPage= new AddressesPage(driver) {
            @Override
            public void open() {

            }

            @Override
            public BasePage isPageOpened() {
                return null;
            }
        };
        authenticationPage= new AuthenticationPage(driver) {
            @Override
            public void open() {

            }

            @Override
            public BasePage isPageOpened() {
                return null;
            }
        };
        homePage = new HomePage(driver) {
            @Override
            public void open() {

            }

            @Override
            public BasePage isPageOpened() {
                return null;
            }
        };
        itemDetailPage=new ItemDetailPage(driver);
        paymentPage= new PaymentPage(driver) {
            @Override
            public void open() {

            }

            @Override
            public BasePage isPageOpened() {
                return null;
            }
        };
        productsPage= new ProductsPage(driver) {
            @Override
            public void open() {

            }

            @Override
            public BasePage isPageOpened() {
                return null;
            }
        };
        shippingPage= new ShippingPage(driver) {
            @Override
            public void open() {

            }

            @Override
            public BasePage isPageOpened() {
                return null;
            }
        };
        cartPage= new CartPage(driver) {
            @Override
            public void open() {

            }

            @Override
            public BasePage isPageOpened() {
                return null;
            }
        };
        createAnAccountPage= new CreateAnAccountPage(driver) {
            @Override
            public BasePage isPageOpened() {
                return null;
            }
        };
        myAccountPage= new MyAccountPage(driver) {
            @Override
            public void open() {

            }

            @Override
            public BasePage isPageOpened() {

                return null;
            }
        };
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
        createAnAccountPage.setPassword(PASSWORD);
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
