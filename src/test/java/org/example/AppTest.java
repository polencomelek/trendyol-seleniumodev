package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class AppTest {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\testinium\\OneDrive\\Masaüstü\\selenium-tutorial\\src\\test\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.trendyol.com/");
    }
    @Test
    public void closePop() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        WebElement product = driver.findElement(By.id("Combined-Shape"));
        product.click();
        TimeUnit.SECONDS.sleep(5);
    }
    @Test
    public void actionsTest() throws InterruptedException {

        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(By.cssSelector("div[class='account-nav-item user-login-container']"))).click().build().perform();
        findElement(By.cssSelector("div[class='login-button']")).click();

    }
@Test
public void login () throws InterruptedException{
    actionsTest();
    WebElement username= findElement(By.id("login-email"));
    username.sendKeys("polen.comelek@testinium.com");

    WebElement password=findElement((By.id("login-password-input")));
    password.sendKeys("Polen1997" + Keys.ENTER);
    TimeUnit.SECONDS.sleep(2);
}
@Test
public void search() throws InterruptedException{
        login();
        WebElement searchBox= findElement(By.className("search-box"));
        TimeUnit.SECONDS.sleep(3);
        searchBox.sendKeys("kazak"+ Keys.ENTER);
        findElement(By.cssSelector("div[class='overlay']")).click();

}

@Test
public void chooseGender() throws InterruptedException {
        search();
        WebElement chooseGender = findElement(By.xpath("//div[@class='fltr_item_wrppr'])[1]"));
        chooseGender.click();
}
    @Test
    public void addFavorite() throws InterruptedException{
        search();
        List<WebElement> imageList = driver.findElements(By.className("fvrt-btn-wrppr"));
        System.out.println(imageList.size());
        String price = findElement(By.cssSelector("div[class='prc-box-sllng']")).getText();
        System.out.println("Price :"+ price);
        WebElement item = imageList.get(9);

        item.click();
    }
    @Test
    public void tenthElement() throws InterruptedException {
        addFavorite();
        List<WebElement> imageList = driver.findElements(By.className("p-card-wrppr"));
        System.out.println(imageList.size());
        WebElement item = imageList.get(9);
        TimeUnit.SECONDS.sleep(3);
        item.click();
        TimeUnit.SECONDS.sleep(3);

    }

@Test
public void addToBasket() throws InterruptedException{
    tenthElement();
    String currentWindow = driver.getWindowHandle();
    wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    Set<String> windowHandles = driver.getWindowHandles();

    for (String window :
            windowHandles) {
        if (!currentWindow.equals(window)) {
            driver.switchTo().window(window);

        }
    }
    TimeUnit.SECONDS.sleep(5);
    findElement(By.cssSelector("button[class='add-to-basket'")).click();
    TimeUnit.SECONDS.sleep(5);
    driver.switchTo().window(currentWindow);

}
@Test


    public WebElement findElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by)); }
    @After

    public void tearDown() {
        driver.quit();
    }
}

