package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
        MY_LISTS_LINK,
        CLOSE_POP_UP_DIALOG,
        OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Open menu by click on hamburger icon")
    public void openNavigation() throws InterruptedException {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation button", 5);
            Thread.sleep(500);
        } else {
            System.out.println("Method openNavigation() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Click on 'My Lists' button")
    public void clickMyLists() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find 'My lists' button",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find 'My lists' button",
                    5
            );
        }
    }

    @Step("Close pop-up dialog")
    public void closePopUpDialog() {
        this.waitForElementAndClick(
                CLOSE_POP_UP_DIALOG,
                "Cannot find and close pop-up dialog",
                5
        );
    }
}
