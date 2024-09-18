package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RentPage extends BasePage {

    public RentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Локатор для выпадающего списка "Срок аренды"
    @FindBy(xpath = ".//div[@class='Dropdown-control']")
    private WebElement rentTermDropdown;

    // Локатор для чекбокса "Черный жемчуг"
    @FindBy(xpath = "//label[@for='black']")
    private WebElement blackPearlCheckbox;

    // Локатор для чекбокса "Серая безысходность"
    @FindBy(xpath = "//label[@for='grey']")
    private WebElement greyDespairCheckbox;

    // Локатор для поля ввода комментария для курьера
    @FindBy(xpath = "//input[@placeholder='Комментарий для курьера']")
    private WebElement courierCommentInput;

    // Локатор для кнопки "Заказать" (первая)
    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private WebElement orderButton;

    // Локатор для кнопки подтверждения заказа "Да" (вторая кнопка)
    @FindBy(xpath = ".//button[text()='Да' and @class='Button_Button__ra12g Button_Middle__1CSJM']")
    private WebElement orderButton2;

    // Локатор для кнопки "Назад"
    @FindBy(xpath = "//button[text()='Назад']")
    private WebElement backButton;

    // Метод для выбора даты доставки самоката
    public void selectDay(String day) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Ожидание до 10 секунд

        // Открываем календарь, кликая на поле даты
        WebElement datePicker = driver.findElement(By.xpath("//input[@placeholder='* Когда привезти самокат']"));
        datePicker.click();

        // Ждём появления календаря
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("react-datepicker")));

        // Выбираем нужный день в календаре и кликаем по нему
        String dayXpath = String.format("//div[contains(@class, 'react-datepicker__day') and text()='%s']", day);
        WebElement dayElement = driver.findElement(By.xpath(dayXpath));
        dayElement.click();
    }

    // Метод для выбора цвета самоката
    public void selectScooterColor(String color) {
        if (color.equalsIgnoreCase("чёрный жемчуг")) {
            blackPearlCheckbox.click();
        } else if (color.equalsIgnoreCase("серая безысходность")) {
            greyDespairCheckbox.click();
        }
    }

    // Метод для ввода комментария для курьера
    public void enterCourierComment(String comment) {
        courierCommentInput.sendKeys(comment);
    }

    // Метод для нажатия первой кнопки "Заказать"
    public RentPage clickOrderButton() {
        orderButton.click();
        return new RentPage(driver);
    }

    // Метод для нажатия второй кнопки "Да" для подтверждения заказа
    public RentPage clickOrderButton2() {
        orderButton2.click();
        return new RentPage(driver);
    }

    // Метод для проверки, что подтверждение заказа отображается на странице
    public boolean isOrderConfirmationDisplayed() {
        driver.findElement(By.xpath("//*[contains(text(),'Заказ оформлен')]"));
        return true;
    }

    // Метод для полного заполнения формы аренды (дата, срок, цвет, комментарий)
    public RentPage fillRentForm(String day, String rentTerm, String color, String comment) {
        selectDay(day);
        selectRentTerm(rentTerm);
        selectScooterColor(color);
        enterCourierComment(comment);
        return new RentPage(driver);
    }

    // Метод для выбора срока аренды
    public void selectRentTerm(String rentTerm) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(rentTermDropdown)).click();
        String rentTermXpath = String.format("//div[@class='Dropdown-option' and text()='%s']", rentTerm);
        WebElement rentTermElement = driver.findElement(By.xpath(rentTermXpath));
        rentTermElement.click();
    }
}