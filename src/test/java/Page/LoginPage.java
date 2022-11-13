package Page;

import Data.DataHelper;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement loginField = $x("//*[@data-test-id='login']//*[@name='login']");
    private final SelenideElement passwordField = $x("//*[@data-test-id='password']//*[@name='password']");
    private final SelenideElement actionLogin = $x("//*[@data-test-id='action-login']");
    private final SelenideElement notificationError = $x("//*[@data-test-id='error-notification']");
    private final SelenideElement closeNotificationError = $x("//*[@data-test-id='error-notification']//*[@class='icon-button__content']");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        loginField.setValue(info.getLogin());
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        passwordField.setValue(info.getPassword());
        actionLogin.click();
        return new VerificationPage();
    }

    public VerificationPage notificationError() {
        notificationError.shouldBe(visible);
        closeNotificationError.click();
        return new VerificationPage();
    }

    public VerificationPage blockedUser() {
        actionLogin.shouldNotBe(visible);
        return new VerificationPage();
    }
}
