package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Локатор для кнопки заказа самоката в верхней части страницы
    @FindBy(className = "Button_Button__ra12g")
    private WebElement orderTopButton;

    // Локатор для кнопки заказа самоката в нижней части страницы
    @FindBy(css = ".Home_FinishButton__1_cWm")
    private WebElement orderBottomButton;

    // Локатор для кнопки "Принять cookies"
    @FindBy(xpath = ".//button[@id='rcc-confirm-button']")
    private WebElement cookies;

    // Метод для клика по конкретному вопросу в FAQ
    // Параметр: текст вопроса, по которому нужно кликнуть
    public void clickOnFAQQuestion(String question) {
        WebElement faqQuestionElement = driver.findElement(By.xpath(String.format("//*[contains(text(),'%s')]", question)));
        faqQuestionElement.click();
    }

    // Метод для проверки, отображается ли корректный текст ответа в FAQ
    // Параметр: текст ответа, который должен быть виден
    // Возвращает true, если текст ответа совпадает с ожидаемым
    public boolean isFAQAnswerTextCorrect(String answer) {
        WebElement faqAnswerElement = driver.findElement(By.xpath(String.format("//*[contains(text(),'%s')]", answer)));
        return faqAnswerElement.getText().equals(answer);
    }

    // Метод для получения элемента ответа в FAQ
    // Параметр: текст ответа, по которому ищется элемент
    // Возвращает WebElement элемента ответа для дальнейшего использования в тесте
    public WebElement getFAQAnswerElement(String answer) {
        return driver.findElement(By.xpath(String.format("//*[contains(text(),'%s')]", answer)));
    }

    // Остальные методы

    // Метод для клика на кнопку заказа самоката в верхней части страницы
    public void clickOnTopOrderButton() {
        orderTopButton.click();
    }

    // Метод для клика на кнопку заказа самоката в нижней части страницы
    public void clickOnBottomOrderButton() {
        orderBottomButton.click();
    }

    // Метод для клика на кнопку "Принять cookies"
    public MainPage clickCookiesButton() {
        cookies.click();
        return this;
    }
}