package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Type word in search and check results")
    @Description("We type 'Java' in search field and check that correct article found")
    @Step("Starting test 'testSearch()'")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Cancel search")
    @Description("Invoke search field and click cancel")
    @Step("Starting test 'testCancelSearch()'")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Cancel search by clear")
    @Description("Invoke search field, type 'Java' and clear search field")
    @Step("Starting test 'testCancelSearchWithClear()'")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearchWithClear() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        if (Platform.getInstance().isMW()) {
            SearchPageObject.clickClearSearch();
        } else {
            SearchPageObject.clickCancelSearch();
        }
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Any search result is present")
    @Description("Type 'Sabaton discography' in search field and check that result of search present")
    @Step("Starting test 'testAmountOfNotEmptySearch()'")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "Sabaton discography";
        SearchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResults = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "Search results not found",
                amountOfSearchResults > 0
        );
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Search get empty result")
    @Description("Type 'sfsfsfsffs' and check that search result empty")
    @Step("Starting test 'testAmountOfEmptySearch()'")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String searchLine = "sfsfsfsffs";
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    /*
    Ex18*: Рефакторинг
    Адаптировать по MW тест на поиск и верификацию трех результатов выдачи поиска.
    */
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Get in search result 3 articles")
    @Description("Type 'Java' in search field, check that in search result we have 3 articles")
    @Step("Starting test 'testCheckResultWithTitleAndDescription()'")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckResultWithTitleAndDescription() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia, Southeast Asia");
        } else {
            SearchPageObject.waitForElementByTitleAndDescription("Java", "Island in Indonesia");
        }
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "High-level programming language");
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
    }
}
