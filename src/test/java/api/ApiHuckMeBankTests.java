package api;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.JsonReader;
import utils.UserTestDataFactory;

import static constants.Constants.TEST_DATA_PATH;
import static io.restassured.RestAssured.given;

public class ApiHuckMeBankTests {



    @Test(groups = "Api tests", dataProvider = "testData")
    public void checkValidDataInput(int numberOfIdenticalRequest, String message) {
        ValidRegisterDataResponse response = null;
        var validData = UserTestDataFactory.getRandomValidUserRegistrationData();
        for (int i = 0; i < numberOfIdenticalRequest; i++) {
            response = registrationRequest(validData, ValidRegisterDataResponse.class);
        }
        assert response != null;
        Assert.assertEquals(response.getText(), message);
    }

    public <T>T registrationRequest(UserRegistrationData data, Class<T> responseClass) {
        Specification.setSpecifications(Specification.requestSpec(JsonReader.getStringParam(TEST_DATA_PATH, "startUrl")), Specification.responseSpec());
        return given()
                .body(data).log().all()
                .when()
                .post("/calluserforsignup")
                .then().log().all()
                .extract()
                .as(responseClass);
    }

    @DataProvider(name = "testData")
    public Object[][] testData() {

        return new Object[][]{
                {1, "Сейчас на ваш телефон поступит звонок, последние 4 цифры являются кодом"},
                {JsonReader.getNumParam(TEST_DATA_PATH, "startUrl"), "Подождите 15 секунд перед повторной авторизации на тот же номер"},
        };
    }
}