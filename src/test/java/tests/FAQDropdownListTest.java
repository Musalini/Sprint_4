package tests;

import base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FAQDropdownListTest extends BaseTest {
    private final String question;
    private final String answer;

    public FAQDropdownListTest(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Параметризация: список вопросов и ответов
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. " +
                        "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                        "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                        "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! " +
                        "Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. " +
                        "Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."} //живу*
        });
    }

    @Test
    public void testFAQDropdownOpens() {
        // Открытие страницы и взаимодействие с элементами через методы MainPage
        MainPage mainPage = new MainPage(driver)
                .clickCookiesButton();

        // Ожидание и проверка видимости ответа до клика
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//*[contains(text(),'%s')]", question))));

        // Проверяем, что ответ не отображается до клика
        Assert.assertFalse("Ответ отображается до клика!", mainPage.isFAQAnswerTextCorrect(answer));

        // Кликаем на вопрос и проверяем, что текст стал видимым
        mainPage.clickOnFAQQuestion(question);

        // Ожидание, пока ответ станет видимым
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("//*[contains(text(),'%s')]", answer))));

        // Проверка, что текст ответа полностью соответствует ожидаемому
        Assert.assertTrue("Ответ не отображается после клика или текст не совпадает!", mainPage.isFAQAnswerTextCorrect(answer));
    }

}
