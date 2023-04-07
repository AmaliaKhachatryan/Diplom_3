package pageobjects;

import org.apache.commons.lang3.RandomStringUtils;

public class GeneratorUser {
    public static DataUser getRandom() {
        String email = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(7);
        return new DataUser(email + "@yandex.ru", password, name);
    }

    public static DataUser getRandomIncorrectPassword() {
        String email = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(5);
        String name = RandomStringUtils.randomAlphabetic(10);
        return new DataUser(email + "@yandex.ru", password, name);
    }

    public static DataUser getRandomWithSexDigitPassword() {
        String email = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(6);
        return new DataUser(email + "@yandex.ru", password, name);
    }

}
