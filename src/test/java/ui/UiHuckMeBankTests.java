package ui;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.pageObject.MainPage;
import ui.pageObject.RegistrationForm;
import ui.pageObject.ValidationCodeForm;
import utils.UserTestDataFactory;

public class UiHuckMeBankTests extends BaseUI {
    Faker faker = new Faker();
    public MainPage mainPage = new MainPage();
    public RegistrationForm registrationForm = new RegistrationForm();
    public ValidationCodeForm validationCodeForm = new ValidationCodeForm();

    @Test(groups = "Ui tests")
    public void checkValidDataInput() {
        Assert.assertTrue(mainPage.isDisplayed(), "Main page is not open");

        registrationForm
                .inputAllData(UserTestDataFactory.getRandomValidUserRegistrationData())
                .clickRegistrationButton()
                .closeAlert();

        Assert.assertTrue(validationCodeForm.isDisplayed());
    }

    @Test(groups = "Ui tests", dataProvider = "testData")
    public void inputInvalidName(String invalidName, String errorMessage) {
        registrationForm
                .inputAllData(UserTestDataFactory.getRandomValidUserRegistrationData())
                .inputName(invalidName)
                .clickRegistrationButton();
        Assert.assertEquals(registrationForm.getNameErrorText(), errorMessage, "Name error message does not match");
    }

    @Test(groups = "Ui tests")
    public void checkSecondAlertMessage() {
        registrationForm
                .inputAllData(UserTestDataFactory.getRandomValidUserRegistrationData())
                .clickRegistrationButton()
                .closeAlert();
        validationCodeForm.exitValidationForm();

        registrationForm.clickRegistrationButton();
        Assert.assertEquals(registrationForm.getAlertText(), "Количество запросов достигло максимума,вы можете отправлять запрос один раз в 35 минут");

    }

    @DataProvider(name = "testData")
    public Object[][] testData() {

        return new Object[][] {
                {faker.letterify("?"), "Ваше имя (ФИО) не должно быть короче 3 символов."},
                {faker.numerify("#"), "Имя может содержать только буквы английского и русского алфавита"},
                {faker.regexify("[a-zA-Z]{" + (30 + faker.number().numberBetween(1, 21)) + "}"), "\"ФИО\" может содержать максимум 30 символов"},

        };
    }
}


