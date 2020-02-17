package definitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dbmanager.DataBaseCon;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Utilities;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Stepdefinitions extends Utilities {
    public static boolean windowMaximize = false;
    public String baseUrl;
    public String nisumAddress;
    public String googleAddress;
    static WebDriver driver;

    @Given("^Go to nisum's website$")
    public void goToNisumSWebsite() throws IOException {
        DataBaseCon db = new DataBaseCon();
        db.initConnection("jdbc:mysql://db4free.net:3306", "akashdktyagi", "akashdktyagi");

        baseUrl = getGlobalValues("baseUrl");
        String dirPath = System.getProperty("user.dir");
        System.setProperty("webdriver.gecko.driver", dirPath + "\\src\\test\\java\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get(baseUrl);
        if (windowMaximize == false) {
            driver.manage().window().maximize();
            windowMaximize = true;
        }

    }

    @When("^Select \"([^\"]*)\" From Our offices seciton$")
    public void selectFromOurOfficesSeciton(String country) {
        // Write code here that turns the phrase above into concrete actions
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,3500)");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.id("block-block-6")));
        WebElement searchCountry = driver.findElement(By.linkText(country));
        searchCountry.click();
        WebElement mainDiv = driver.findElement(By.id("pills-pakistan"));
        nisumAddress = mainDiv.findElement(By.cssSelector("span")).getText();

    }

    @Then("^Save \"([^\"]*)\" office address$")
    public void saveOfficeAddress(String arg0) {

    }

    @When("^Go to \"([^\"]*)\" and Type \"([^\"]*)\"$")
    public void goToAndType(String website, String searchKey) {
        driver.navigate().to(website);
        WebElement as = driver.findElement(By.cssSelector("input[name='q']"));
        as.sendKeys(searchKey);
        as.submit();

    }

    @Then("^Web page loads$")
    public void webPageLoads() {
        // WebDriverWait  wait = new WebDriverWait(driver,10);
        /*Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);*/
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement div = driver.findElement(By.className("i4J0ge"));

    }

    @And("^pick Nisum Address$")
    public void pickNisumAddress() {
        WebElement div = driver.findElement(By.className("i4J0ge"));
        googleAddress = div.findElement(By.cssSelector("span[class='LrzXr']")).getText();
    }

    @And("^Assert both addressess$")
    public void assertBothAddressess() {
        String nisumAddressSubstr = nisumAddress.substring(6, 36);
        String googleAddressSubstr = googleAddress.substring(11, 41);
        String replaceChargoogle = googleAddressSubstr.replace("،", ",");
        Assert.assertEquals(nisumAddressSubstr, replaceChargoogle);

    }
}





