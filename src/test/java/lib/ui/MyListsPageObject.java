package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL,
        ARTICLE_BY_FOOTER_TPL,
        REMOVE_FROM_SAVED,
        REMOVE_FROM_SAVED_BUTTON,
        EDIT_BUTTON,
        UNSAVE_BUTTON,
        DELETE_BUTTON,
        NO_SAVED_PAGES_YET;

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    private static String getSavedArticleXpathByFooter(String article_footer) {
        return ARTICLE_BY_FOOTER_TPL.replace("{FOOTER}", article_footer);
    }

    public MyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Open folder '{name_of_folder}'")
    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getSavedArticleXpathByTitle(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name '" + name_of_folder + "'",
                5
        );
    }

    @Step("Waiting article with title '{article_title}'")
    public void waitForArticleToAppearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }

    @Step("Waiting article with footer '{article_footer}'")
    public void waitForArticleToAppearByFooter(String article_footer) {
        String article_xpath = getSavedArticleXpathByTitle(article_footer);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_footer, 15);
    }

    @Step("Waiting article with title '{article_title}' to disappear")
    public void waitForArticleToDisappearByTitle(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 15);
    }

    @Step("Swipe article '{article_title}' to delete")
    public void swipeByArticleToDelete(String article_title) throws InterruptedException {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
            Thread.sleep(500);
        }

        if (Platform.getInstance().isIOS()){
            this.waitForElementAndClick(DELETE_BUTTON, "Cannot find element", 5);
            this.waitForElementPresent(NO_SAVED_PAGES_YET, "Saved articles list not empty", 5);
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    @Step("Swipe article '{article_footer}' to delete")
    public void swipeByArticleFooterToDelete(String article_footer) throws InterruptedException {
        this.waitForArticleToAppearByTitle(article_footer);
        String article_xpath = getSavedArticleXpathByTitle(article_footer);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_footer);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
            Thread.sleep(500);
        }

        if (Platform.getInstance().isIOS()){
            this.waitForElementAndClick(DELETE_BUTTON, "Cannot find element", 5);
        }
        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(article_footer);
    }

    @Step("Click on article '{article_title}' to delete")
    public void clickByArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndOpenContextMenu(article_xpath, "Cannot find saved article");
        this.waitForElementAndClick(REMOVE_FROM_SAVED, "Cannot find and click Remove button", 5);
        this.waitForArticleToDisappearByTitle(article_title);
    }

    @Step("Unsave article '{article_title}' to delete")
    public void unSaveArticle(String article_title) {
        this.waitForElementAndClick(EDIT_BUTTON, "Cannot find and click on Edit button", 5);
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(article_xpath, "Cannot find saved article", 5);
        this.waitForElementAndClick(UNSAVE_BUTTON, "Cannot find and click on Unsave button", 5);
        this.waitForArticleToDisappearByTitle(article_title);
    }
}
