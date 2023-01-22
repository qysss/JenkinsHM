
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Opening main page")
    void openMainPage() {
        open("https://github.com");
    }

    @Step("Searching for repository {repo}")
    void searchForRepository(String repo) {
        $(".header-search-input").setValue(repo).pressEnter();
    }

    @Step("Clicking on repository link {repo}")
    void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Opening issues tab")
    void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Checking for issue with number {issue}")
    void shouldSeeIssueWithNumber(int issue) {
        $(withText("#" + issue)).shouldBe(visible);
    }

}