import com.sun.source.tree.PackageTree;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.List;

public class AramAntonyanTest extends BaseTest {

    @Test
    public void testSearchForLanguageByName_HappyPath() {
        final String BASE_URL = "https://www.99-bottles-of-beer.net/";
        final String LANGUAGE_NAME = "python";


        getDriver().get(BASE_URL);

        WebElement searchLanguagesMenu = getDriver().findElement(
                By.xpath("//ul[@id = 'menu']/li/a[@href='/search.html']")
        );

        searchLanguagesMenu.click();

        WebElement searchForField = getDriver().findElement(
                By.name("search")
        );

        searchForField.click();
        searchForField.sendKeys(LANGUAGE_NAME);

        WebElement goButton = getDriver().findElement(
                By.name("submitsearch")
        );

        goButton.click();

        List<WebElement> languagesNamesList = getDriver().findElements(
                By.xpath("//table[@id='category']/tbody/tr/td[1]/a")
        );

        Assert.assertTrue(languagesNamesList.size() > 0);

        for (int i = 0; i < languagesNamesList.size(); i++) {
            Assert.assertTrue(languagesNamesList.get(i).getText().toLowerCase().contains(LANGUAGE_NAME));

        }
    }

    @Test
    public void testTitleIsCorrect_HappyPath() {
        final String BASE_URL = "https://www.99-bottles-of-beer.net/";
        String expectedResult = "99 Bottles of Beer | Start";

        getDriver().get(BASE_URL);
        String actualResult = getDriver().getTitle();

        Assert.assertEquals(actualResult, expectedResult);


    }

    @Test
    public void testBaseUrlIsCorrect_HappyPath() {
        final String BASE_URL = "https://www.99-bottles-of-beer.net/";
        String expectedResult = "https://www.99-bottles-of-beer.net/";

        getDriver().get(BASE_URL);
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testTeamPageName_HappyPath() {
        final String BASEURL = "https://www.99-bottles-of-beer.net/";
        String expectedResult = "The Team";

        getDriver().get(BASEURL);
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
