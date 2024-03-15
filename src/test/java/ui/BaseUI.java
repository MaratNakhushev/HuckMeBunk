package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.JsonReader;

import static constants.Constants.TEST_CONFIG_PATH;
import static constants.Constants.TEST_DATA_PATH;

public class BaseUI {

    public void setUp() {
        Configuration.browser = JsonReader.getStringParam(TEST_CONFIG_PATH, "browserType");
        Configuration.browserSize = JsonReader.getStringParam(TEST_CONFIG_PATH, "browserSize");
        Configuration.headless = JsonReader.getBoolParam(TEST_CONFIG_PATH, "headLess");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeMethod
    public void init() {
        setUp();
        Selenide.open(JsonReader.getStringParam(TEST_DATA_PATH, "startUrl"));
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
