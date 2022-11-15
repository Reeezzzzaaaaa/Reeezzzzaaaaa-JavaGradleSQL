package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {

    private final SelenideElement codeField = $x("//*[@data-test-id='code']//input");
    private final SelenideElement actionVerify = $x("//*[@data-test-id='action-verify']");

    public DashBoardPage validCode(String code) {
        codeField.setValue(code);
        actionVerify.click();
        return new DashBoardPage();
    }
}
