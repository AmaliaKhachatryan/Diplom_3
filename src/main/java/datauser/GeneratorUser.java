package datauser;

import datauser.DataUser;
import org.apache.commons.lang3.RandomStringUtils;

public class GeneratorUser {
    static int pwdCharacters=10;

    public static DataUser getRandom() {
        String email = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(pwdCharacters);
        String name = RandomStringUtils.randomAlphabetic(7);
        return new DataUser(email + "@yandex.ru", password, name);
    }

    public static DataUser getRandom(int pwdCharacters) {
        String email = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(pwdCharacters);
        String name = RandomStringUtils.randomAlphabetic(10);
        return new DataUser(email + "@yandex.ru", password, name);
    }
}
