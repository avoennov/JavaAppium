package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        CLOSE_ARTICLE_BUTTON = "xpath://XCUIElementTypeButton[@name='W']";
        ARTICLE_FOOTER = "id:{SUBSTRING}";
        BACK_BUTTON = "id:Back";

        TITLE = "css:#content h1";
        FOOTER_ELEMENT = "css:.footer-content";
        LOGIN_MAIN_MENU = "xpath://span[.='Log in']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "css:a#ca-watch";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "xpath://a[@id='ca-watch'][@title='Remove this page from your watchlist']";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
