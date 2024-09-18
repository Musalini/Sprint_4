package tests;

import base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;
import pages.OrderPage;
import pages.RentPage;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phoneNumber;
    private final String metroStation;
    private final String deliveryDay;
    private final String rentTerm;
    private final String color;
    private final String comment;

    public OrderTest(String firstName, String lastName, String address, String phoneNumber, String metroStation,
                     String deliveryDay, String rentTerm, String color, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.metroStation = metroStation;
        this.deliveryDay = deliveryDay;
        this.rentTerm = rentTerm;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"Муса", "Мурадзаде", "Московский проспект", "89115263456", "Чертановская", "12", "трое суток", "чёрный жемчуг", "Комментарий для курьера один"},
                {"Виктор", "Лутчин", "Комендантский проспект", "89111255635", "Сокольники", "25", "сутки", "серая безысходность", "Комментарий для курьера два"},
        };
    }

    // Нажатие на верхнюю кнопку "Заказать"
    @Test
    public void testOrderTopButton() {
        placeOrder(true);
    }

    // Нажатие на нижнюю кнопку "Заказать"
    @Test
    public void testOrderBottomButton() {
        placeOrder(false);
    }

    private void placeOrder(boolean isTopButton) {
        MainPage mainPage = new MainPage(driver).clickCookiesButton();
        if (isTopButton) {
            mainPage.clickOnTopOrderButton();
        } else {
            mainPage.clickOnBottomOrderButton();
        }

        new OrderPage(driver)
                .fillOrderForm(firstName, lastName, address, metroStation, phoneNumber)
                .clickNextButton();

        RentPage rentPage = new RentPage(driver)
                .fillRentForm(deliveryDay, rentTerm, color, comment)
                .clickOrderButton()
                .clickOrderButton2();

        Assert.assertTrue(rentPage.isOrderConfirmationDisplayed());
    }
}
