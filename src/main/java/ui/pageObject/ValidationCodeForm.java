package ui.pageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ValidationCodeForm extends BaseForm {

    private final SelenideElement exitFormButton = $(By.id("closeImg"));

    public ValidationCodeForm() {
        super($(By.id("loginModalAuth")));
    }

    public void exitValidationForm() {
        exitFormButton.click();
    }
}