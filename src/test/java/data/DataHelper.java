package data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {

    private static Faker faker = new Faker(new Locale("en"));

    DataHelper() {
    }

    public static AuthInfo getAuthInfoVasya() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getAuthInfoVasyaWithRandomPassword() {
        return new AuthInfo("vasya", getRandomPassword());
    }

    public static String getRandomLogin() {
        return faker.name().username();
    }

    public static String getRandomPassword() {
        return faker.internet().password();
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo(getRandomLogin(), getRandomPassword());
    }

    public static String getRandomVerificationCode() {
        return String.valueOf(new VerificationCode(faker.numerify("######")));
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class AuthCode {
        private String id;
        private String user_id;
        private String code;
        private String created;
    }
}
