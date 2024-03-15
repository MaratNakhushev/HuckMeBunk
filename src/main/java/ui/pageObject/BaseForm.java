package ui.pageObject;

import com.codeborne.selenide.SelenideElement;

public abstract class BaseForm {

    private final SelenideElement uniqElement;

    protected BaseForm(SelenideElement uniqElement) {
        this.uniqElement = uniqElement;
    }

    public boolean isDisplayed() {
        return uniqElement.isDisplayed();
    }
}