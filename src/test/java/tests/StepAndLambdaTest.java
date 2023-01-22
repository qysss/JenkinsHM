package tests;

import com.codeborne.selenide.Condition;
import helpers.TestBase;
import helpers.WebSteps;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepAndLambdaTest extends TestBase {

    WebSteps steps = new WebSteps();
    private static final String REPOSITORY = "qysss/AllureHM";
    private static final int ISSUE = 1;

    @Test
    public void testLamdaStep() {
        step("Opening main page", () -> open("https://github.com"));

        step("Searching for desired repository" + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Clicking on repository link", () -> {
            $(linkText(REPOSITORY)).click();
            $("#issues-tab").click();
        });
        step("Checking for the presence of issue" + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });

    }

    @Test
    void annotatedStepTest() {
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);

    }

}

