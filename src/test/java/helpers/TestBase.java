package helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

public class TestBase {
    String asd = System.getProperty("browserName","chrome");
    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        new ChromeOptions().addArguments("start-maximized");
        Configuration.baseUrl = System.getProperty("base_url","https://demoqa.com");
        Configuration.browser = System.getProperty("browserName","chrome");
        Configuration.remote = System.getProperty("remoteUrl","https://user1:1234@selenoid.autotests.cloud/wd/hub");
        Configuration.browserSize = System.getProperty("browserSize","1320x800");
        Configuration.browserVersion = System.getProperty("browserVersion","100");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", Configuration.browser);
        capabilities.setCapability("browserVersion", Configuration.browserVersion);
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        RemoteWebDriver driver = new RemoteWebDriver(
                URI.create(Configuration.remote).toURL(),
                capabilities
        );
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        ListAttachments.screenshotAs("Last screenshot");
        ListAttachments.pageSource();
        ListAttachments.browserConsoleLogs();
        ListAttachments.addVideo();
    }
}
