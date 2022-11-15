import data.DataHelper;
import data.DbUtils;
import page.DashBoardPage;
import page.LoginPage;
import page.VerificationPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static data.DbUtils.cleanDB;
import static com.codeborne.selenide.Selenide.open;

public class UserVerifyTest {

    @BeforeEach
    void openPage() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
    }

    @AfterAll
    public static void tearDown() {
        cleanDB();
    }

    @Test
    void shouldSuccessTestDataTest() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfoVasya());
        VerificationPage verify = new VerificationPage();
        verify.validCode(String.valueOf(DbUtils.getVerificationCode()));
        DashBoardPage dashBoard = new DashBoardPage();
        dashBoard.dashboard();
    }

    @Test
    void shouldUnSuccessRandomDataTest() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfo());
        login.notificationError();
    }

    @Test
    void shouldUnSuccessRandomVerificationTest() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfoVasya());
        VerificationPage verify = new VerificationPage();
        verify.validCode(String.valueOf(DataHelper.getRandomVerificationCode()));
    }

    @Test
    void shouldUnSuccessInvalidPassword3TimesTest() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfoVasyaWithRandomPassword());
        login.notificationError();
        login.validLogin(DataHelper.getAuthInfoVasyaWithRandomPassword());
        login.notificationError();
        login.validLogin(DataHelper.getAuthInfoVasyaWithRandomPassword());
        login.blockedUser();
    }
}