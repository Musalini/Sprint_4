package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OrderPage extends BasePage {

    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Локатор поля для ввода имени
    @FindBy(xpath = "//input[@placeholder='* Имя']")
    private WebElement firstNameInput;

    // Локатор поля для ввода фамилии
    @FindBy(xpath = "//input[@placeholder='* Фамилия']")
    private WebElement lastNameInput;

    // Локатор поля для ввода адреса доставки
    @FindBy(xpath = "//input[@placeholder='* Адрес: куда привезти заказ']")
    private WebElement addressInput;

    // Локатор поля для выбора станции метро
    @FindBy(xpath = "//input[@placeholder='* Станция метро']")
    private WebElement metroStationInput;

    // Локатор списка станций метро
    private By metroStationList = By.xpath("//div[@class='select-search__select']");

    // Локатор поля для ввода телефона
    @FindBy(xpath = "//input[@placeholder='* Телефон: на него позвонит курьер']")
    private WebElement phoneInput;

    // Локатор кнопки "Далее"
    @FindBy(xpath = "//button[text()='Далее']")
    private WebElement nextButton;

    // Метод для выбора станции метро по её названию
    public void selectMetroStation(String stationName) {
        metroStationInput.click(); // Кликаем по полю выбора станции метро
        wait.until(ExpectedConditions.visibilityOfElementLocated(metroStationList)); // Ждем появления списка станций
        // Получаем список всех станций и перебираем их, чтобы выбрать нужную
        List<WebElement> stations = driver.findElements(By.xpath("//div[@class='select-search__select']//div[@class='Order_Text__2broi']"));
        for (WebElement station : stations) {
            if (station.getText().equals(stationName)) {
                station.click();
                break;
            }
        }
    }

    // Метод для заполнения формы заказа. Принимает имя, фамилию, адрес, станцию метро и телефон
    public OrderPage fillOrderForm(String firstName, String lastName, String address, String metroStation, String phone) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        addressInput.sendKeys(address);
        selectMetroStation(metroStation);
        phoneInput.sendKeys(phone);
        return new OrderPage(driver);
    }

    // Метод для нажатия кнопки "Далее"
    public void clickNextButton() {
        nextButton.click();
    }
}
