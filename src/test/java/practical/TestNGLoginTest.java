package practical;

import java.io.File;
import java.nio.file.Path;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGLoginTest {

    private WebDriver driver;
    private String pageUrl;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        driver = new ChromeDriver(options);

        pageUrl = Path.of(
                "src", "test", "resources", "login.html"
        ).toAbsolutePath().toUri().toString();

        driver.get(pageUrl);
    }

    @Test(priority = 1, groups = {"smoke"})
    public void verifyPageTitle() {
        Assert.assertEquals(
                driver.getTitle(),
                "STQA Student Login",
                "Page title should match"
        );
    }

    @Test(priority = 2, groups = {"smoke"})
    public void verifyUsernameField() {
        Assert.assertTrue(
                driver.findElement(By.id("username")).isDisplayed(),
                "Username field should be visible"
        );
    }

    @Test(priority = 3, groups = {"regression"})
    public void verifyPasswordField() {
        Assert.assertTrue(
                driver.findElement(By.id("password")).isDisplayed(),
                "Password field should be visible"
        );
    }

    @Test(priority = 4, groups = {"smoke", "regression"})
    public void verifySuccessfulLogin() {
        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("test123");
        driver.findElement(By.id("loginButton")).click();

        Assert.assertEquals(
                driver.findElement(By.id("message")).getText(),
                "Login successful!"
        );
    }

    @Test(priority = 5, groups = {"regression"})
    public void verifyInvalidLogin() {
        driver.findElement(By.id("username")).sendKeys("wronguser");
        driver.findElement(By.id("password")).sendKeys("wrongpass");
        driver.findElement(By.id("loginButton")).click();

        Assert.assertEquals(
                driver.findElement(By.id("message")).getText(),
                "Invalid username or password."
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
