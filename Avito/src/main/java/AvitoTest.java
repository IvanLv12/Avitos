package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AvitoTest {
    protected WebDriver driver;

    // Метод для настройки драйвера и открытия сайта
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:/Users/Ivanlv/Downloads/geckodriver-v0.35.0-win64");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.avito.ru/#login?authsrc=h");

        // Нажать кнопку "Вход и регистрация"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ожидание появления элемента
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-marker='header/login-button']")));
        loginButton.click();
    }

    // Метод для тестирования регистрации нового пользователя
    public void testRegistration() {
        // Вводим данные для регистрации
        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("testuser@example.com");

        WebElement phoneField = driver.findElement(By.id("phone"));
        phoneField.sendKeys("1234567890");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("securePassword123");

        // Нажимаем кнопку регистрации
        WebElement registerSubmitButton = driver.findElement(By.id("registerSubmit"));
        registerSubmitButton.click();

       
        WebElement smsCodeField = driver.findElement(By.id("smsCode"));
        smsCodeField.sendKeys("123456"); // Пример кода

        WebElement confirmButton = driver.findElement(By.id("confirmCode"));
        confirmButton.click();

        // Входим в личный кабинет
        WebElement profileButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Личный кабинет')]")));
        profileButton.click();

        // Проверяем корректность данных профиля
        WebElement emailProfile = driver.findElement(By.id("profileEmail"));
        assert emailProfile.getText().equals("testuser@example.com");

        WebElement phoneProfile = driver.findElement(By.id("profilePhone"));
        assert phoneProfile.getText().equals("1234567890");
    }

    // Метод для завершения работы с браузером
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
