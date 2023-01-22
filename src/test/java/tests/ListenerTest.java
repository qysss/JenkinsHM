package tests;

import com.codeborne.selenide.Condition;
import helpers.TestBase;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class ListenerTest extends TestBase {

    @Test
    @Tag("smoke")
    public void listenerTestIssueSearch() {
        open("https://github.com");
        $(".header-search-input").setValue("qysss/AllureHM").submit();
        $(linkText("qysss/AllureHM")).click();
        $("#issues-tab").click();
        $(withText("#1")).should(Condition.exist);
    }
}