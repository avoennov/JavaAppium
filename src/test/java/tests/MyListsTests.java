package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Tests for articles")
public class MyListsTests extends CoreTestCase {

    private static String
            name_of_folder = "Learning programming",
            search_line = "Java",
            article_footer_1 = "Object-oriented programming language",
            article_footer_2 = "Island in Indonesia",
            article_title_1 = "Java (programming language)",
            article_title_2 = "Java";

    @Test
    @Features(value = {@Feature(value = "My Lists"), @Feature(value = "Article")})
    @DisplayName("Add article to My List")
    @Description("We open 'Java Object-oriented programming language' article and add it to My List." +
            "Open My List and check that this article is present. Remove this article from list.")
    @Step("Starting test 'testSaveFirstArticleToMyList'")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveFirstArticleToMyList() throws InterruptedException {
        loginToWikiForMobileWeb();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_footer_1);

        Thread.sleep(500);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();
        if (Platform.getInstance().isIOS()) {
            NavigationUI.closePopUpDialog();
        }

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        Thread.sleep(500);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    /*
    Ex17: Рефакторинг
    Адаптировать под MW тест на удаление одной сохраненной статьи из двух. Вместо проверки title-элемента придумать другой
    способ верификации оставшейся статьи (т.е. способ убедиться, что осталась в сохраненных ожидаемая статья).
    Результат выполнения задания нужно закоммитить в git. В качестве ответа прислать ссылку на коммит. Если вам потребовалось
    несколько коммитов для выполнения одного задания - присылайте ссылки на все эти коммиты с комментариями.*/
    @Test
    @Features(value = {@Feature(value = "My Lists"), @Feature(value = "Article")})
    @DisplayName("Add two articles to My List")
    @Description("We open 'Java Object-oriented programming language' article and add it to My List." +
            "Open 'Java Island in Indonesia' article and add it to My List." +
            "Open My List and check that this articles are present." +
            "Remove 'Java Island in Indonesia' article from list.")
    @Step("Starting test 'testSaveTwoArticlesToMyList'")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveTwoArticlesToMyList() throws InterruptedException {
        loginToWikiForMobileWeb();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(article_footer_1);

        Thread.sleep(500);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_footer = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.clickBackButton();
        } else {
            ArticlePageObject.addArticlesToMySaved();
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(search_line);
        }
        SearchPageObject.clickByArticleWithSubstring(article_footer_2);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isMW()) {
            NavigationUI.openNavigation();
        }
        NavigationUI.clickMyLists();
        if (Platform.getInstance().isIOS()) {
            NavigationUI.closePopUpDialog();
        }

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            MyListsPageObject.swipeByArticleFooterToDelete(article_footer_2);
            Thread.sleep(500);
            MyListsPageObject.waitForArticleToAppearByFooter(article_footer_1);
        } else {
            MyListsPageObject.swipeByArticleFooterToDelete(article_title_2);
            Thread.sleep(500);
            MyListsPageObject.waitForArticleToAppearByFooter(article_title_1);
        }
    }
}
