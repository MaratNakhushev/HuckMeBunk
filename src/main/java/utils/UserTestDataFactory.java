package utils;

import api.UserRegistrationData;
import com.github.javafaker.Faker;


public class UserTestDataFactory {
    private static final Faker faker = new Faker();

    public static UserRegistrationData getRandomValidUserRegistrationData() {
        var password = faker.internet().password(8, 12);
        var firstNumOfPhoneNumber = "89";

        return new UserRegistrationData(
                faker.name().firstName(),
                faker.internet().emailAddress(),
                firstNumOfPhoneNumber + faker.numerify("#########"),
                password,
                password
        );
    }
}