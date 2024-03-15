package ui.pageObject;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage extends BaseForm {

    public MainPage() {
        super($x("//div[contains(@class,'bankWrapper')]"));
    }
}