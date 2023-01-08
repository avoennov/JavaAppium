package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_FOOTER_TPL = "id:{FOOTER}";
        REMOVE_FROM_SAVED = "id:Remove from saved";
        EDIT_BUTTON = "id:Edit";
        UNSAVE_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Unsave']";
        DELETE_BUTTON = "id:swipe action delete";
        NO_SAVED_PAGES_YET= "id:No saved pages yet";

        ARTICLE_BY_TITLE_TPL = "css:li[title='{TITLE}']";
        REMOVE_FROM_SAVED_BUTTON = "xpath://div[@id='mw-content-text']//li[@title='{TITLE}']//a[contains(@class, 'mw-ui-icon')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
