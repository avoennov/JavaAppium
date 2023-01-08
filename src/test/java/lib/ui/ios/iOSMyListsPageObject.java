package lib.ui.ios;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{TITLE}')]";
        ARTICLE_BY_FOOTER_TPL = "id:{FOOTER}";
        REMOVE_FROM_SAVED = "id:Remove from saved";
        EDIT_BUTTON = "id:Edit";
        UNSAVE_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Unsave']";
        DELETE_BUTTON = "id:swipe action delete";
        NO_SAVED_PAGES_YET= "id:No saved pages yet";
    }

    public iOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
