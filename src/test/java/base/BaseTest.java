package base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        // Получаем браузер из системных свойств
        String browser = System.getProperty("browser", "chrome"); // По умолчанию Chrome

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().clearDriverCache().setup();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().clearDriverCache().setup();
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
        }

        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
