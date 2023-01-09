package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic("Tests for welcome screen")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Welcome screen")})
    @DisplayName("Go through welcome screen")
    @Description("We open welcome screen and click Next on each page")
    @Step("Starting test 'testPassThroughWelcome()'")
    @Severity(value = SeverityLevel.MINOR)
    public void testPassThroughWelcome() {
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())) {
            return;
        }
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWaysToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitAddOrEditPreferredLanguagesText();
        WelcomePage.clickNextButton();

        WelcomePage.waitLearnMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedButton();
    }
}
