import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class ListenerTest {

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @Test
    public void listenerTestIssueSearch() {

        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys("qysss/AllureHM");
        $(".header-search-input").submit();

        $(linkText("qysss/AllureHM")).click();
        $("#issues-tab").click();
        $(withText("#1")).should(Condition.exist);

    }

}