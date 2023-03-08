import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class AramAntonyanTest extends BaseTest {


    final String BASE_URL = "https://www.99-bottles-of-beer.net/";

    final static By SEARCH_LANGUAGES_MENU = By.xpath("//ul[@id = 'menu']/li/a[@href='/search.html']");
    final static By SEARCH_FOR_FIELD = By.name("search");
    final static By GO_BUTTON = By.name("submitsearch");
    final static By LANGUAGES_NAMES_LIST =  By.xpath("//table[@id='category']/tbody/tr/td[1]/a");




    private void openBaseUrl(WebDriver driver) {
        driver.get(BASE_URL);
    }

    private WebElement getElement(By by, WebDriver driver) {

        return driver.findElement(by);
    }

    private List<WebElement> getListOfElements(By by, WebDriver driver) {

        return driver.findElements(by);
    }

    private void click(By by, WebDriver driver) {
        getElement(by, driver).click();
    }

    private void input(String text, By by, WebDriver driver) {
        getElement(by, driver).sendKeys(text);
    };

    private int getListSize (By by, WebDriver driver) {
        return getListOfElements(by, driver).size();
    }

    private int getListSize (List<String> list) {
        return list.size();
    }
    private List<String> getElementsText (By by, WebDriver driver) {
        List<WebElement> elementsList = getListOfElements(by, driver);
        List<String> textList = new ArrayList<>();

//        for (int i = 0; i < elementsList.size(); i++) {
//        textList.add(elementsList.get(i).getText().toLowerCase());
//        }
        for (WebElement element : elementsList) {
            textList.add(element.getText().toLowerCase());
        }
        return textList;

    }


    @Test
    public void testSearchForLanguageByName_HappyPath() throws InterruptedException {
        final String LANGUAGE_NAME = "python";

        openBaseUrl(getDriver());

//        WebElement searchLanguagesMenu = getElement(SEARCH_LANGUAGES_MENU, getDriver());
//        searchLanguagesMenu.click();
        click(SEARCH_LANGUAGES_MENU, getDriver());

//        WebElement searchForField = getElement(SEARCH_FOR_FIELD, getDriver());
//        searchForField.click();
        click(SEARCH_FOR_FIELD, getDriver());

//        searchForField.sendKeys(LANGUAGE_NAME);
        input(LANGUAGE_NAME, SEARCH_FOR_FIELD, getDriver());

//        WebElement goButton = getElement(GO_BUTTON, getDriver());
//        goButton.click();
        click(GO_BUTTON, getDriver());

//        List<WebElement> languagesNamesList = getDriver().findElements(
//                By.xpath("//table[@id='category']/tbody/tr/td[1]/a")
//        );

//        List<WebElement> languagesNamesList = getListOfElements(LANGUAGES_NAMES_LIST, getDriver());

        List<String> languageNames = getElementsText(LANGUAGES_NAMES_LIST, getDriver());

        int sizeOfLangNameLIst = getListSize(languageNames);
        Assert.assertTrue(sizeOfLangNameLIst > 0);

        for (String languageName : languageNames) {
            Assert.assertTrue(languageName.contains(LANGUAGE_NAME));

        }
    }

    @Test
    public void testTitleIsCorrect_HappyPath() {
        String expectedResult = "99 Bottles of Beer | Start";

        openBaseUrl(getDriver());

        getDriver().get(BASE_URL);
        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);


    }

    @Test
    public void testBaseUrlIsCorrect_HappyPath() {
        String expectedResult = "https://www.99-bottles-of-beer.net/";

        openBaseUrl(getDriver());
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTeamPageName_HappyPath() {

        String expectedResult = "The Team";

        openBaseUrl(getDriver());
        WebElement teamButton = getDriver().findElement(
                By.xpath("//ul[@id='submenu']//li//a[@href='team.html']")
        );

        teamButton.click();

        String actualResult = getDriver().findElement(
                By.xpath("//div[@id='main']/h2[text()='The Team']")
        ).getText();

        Assert.assertEquals(actualResult, expectedResult);
    }
}
