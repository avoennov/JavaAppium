package lib.ui.ios;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI {
    static {
        MY_LISTS_LINK = "id:Saved";
        CLOSE_POP_UP_DIALOG = "id:Close";
    }

    public iOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
