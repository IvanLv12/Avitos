package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.avito.ru/");
    }

    @Test
    public void testUserRegistration() {
        driver.findElement(By.xpath("//a[contains(text(),'Войти')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Зарегистрироваться')]")).click();

        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("testuser@example.com");

        WebElement phoneField = driver.findElement(By.name("phone"));
        phoneField.sendKeys("9999999999");

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("TestPassword123");

        driver.findElement(By.xpath("//button[contains(text(),'Зарегистрироваться')]")).click();

        // Ожидание ввода SMS-кода (имитация)
        try {
            Thread.sleep(5000); // Подождем 5 секунд (в реальности можно сделать ввод кода вручную)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement profileButton = driver.findElement(By.xpath("//a[contains(text(),'Профиль')]"));
        Assertions.assertTrue(profileButton.isDisplayed(), "Регистрация не удалась!");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
