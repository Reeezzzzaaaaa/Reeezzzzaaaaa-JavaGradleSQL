package page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class DashBoardPage {

    private final SelenideElement dashBoard = $x("//*[@data-test-id='dashboard']");

    public DashBoardPage dashboard() {
        dashBoard.shouldBe(visible, Duration.ofSeconds(15));
        return new DashBoardPage();
    }
}
