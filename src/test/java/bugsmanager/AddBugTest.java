package bugsmanager;

import Infrastructure.base.TestBase;
import Infrastructure.pages.MainPage;
import Infrastructure.utils.WaitUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class AddBugTest extends TestBase {

    @Test
    public void firstBugTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage(server.getUrl());
        logger.log("check first bug in table");
        Assertions.assertThat(mainPage.getFirstBug()).isEqualTo("First Bug");
    }

    @Test
    public void addNewBugTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage(server.getUrl());
        driver.manage().window().maximize();
        logger.log("add new bug");
        mainPage.addNewBug();
        Assertions.assertThat(mainPage.getNewBug()).isEqualTo("Bug is exist");
        logger.log("delete new bug");
        mainPage.deleteNewBug();
        driver.navigate().refresh();
        Assertions.assertThat(mainPage.getNewBug()).isEqualTo("Bug isn't exist");
    }

    @Test
    public void addNewBugFromFormTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage(server.getUrl());
        driver.manage().window().maximize();
        logger.log("add new bug");
        mainPage.addBugFromForm();
        WaitUtils.waitABit(500);
        WaitUtils.waitForLoad(driver);
        Assertions.assertThat(mainPage.getNewBug()).isEqualTo("Bug is exist");
        logger.log("delete new bug");
        mainPage.deleteNewBug();
        driver.navigate().refresh();
        Assertions.assertThat(mainPage.getNewBug()).isEqualTo("Bug isn't exist");
    }
}

