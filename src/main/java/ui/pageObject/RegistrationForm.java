package ui.pageObject;

import api.UserRegistrationData;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationForm extends BaseForm {

    private final SelenideElement nameField = $(By.id("userName"));
    private final SelenideElement emailField = $(By.id("email"));
    private final SelenideElement phoneNumberField = $(By.id("phoneNumber"));
    private final SelenideElement passwordField = $(By.id("password"));
    private final SelenideElement passwordValidationField = $(By.id("passwordValidation"));
    private final SelenideElement registerButton = $(By.id("submitLogin"));
    private final SelenideElement invalidNameMassage = nameField
            .closest(".signUpForm__data")
            .find(".signUpForm__errText");

    public RegistrationForm() {
        super($x("//form[@name='inputForAuth']"));
    }

    public RegistrationForm inputAllData(UserRegistrationData allData) {
        inputName(allData.getUserName());
        inputEmail(allData.getLogin());
        inputPhoneNumber(allData.getPhoneNumber());
        inputPassword(allData.getPassword());
        inputPasswordValidation(allData.getPasswordValidation());
        return this;
    }

    public RegistrationForm inputName(String name) {
        nameField.setValue(name);
        return new RegistrationForm();
    }

    public void inputEmail(String email) {
        emailField.setValue(email);

    }

    public void inputPhoneNumber(String phoneNumber) {
        phoneNumberField.setValue(phoneNumber);
    }

    public void inputPassword(String password) {
        passwordField.setValue(password);
    }

    public void inputPasswordValidation(String passwordValidation) {
        passwordValidationField.setValue(passwordValidation);
    }

    public RegistrationForm clickRegistrationButton() {
        registerButton.click();
        return this;
    }

    public void closeAlert() {
        switchTo().alert().accept();
    }

    public String getAlertText() {
        return switchTo().alert().getText();
    }

    public String getNameErrorText() {
        return invalidNameMassage.should(Condition.appear).getText();
    }
}