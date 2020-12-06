package Infrastructure.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BugsTable {

    private static final String BUG_NAME = "//table/tbody/tr/td[3]";
    private final WebDriver driver;


    public BugsTable(WebElement element, WebDriver driver) {
        this.driver = driver;
    }


    public List<String> getNameBugFromTable() {
        List<String> actualBugs = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath(BUG_NAME));
        for (WebElement webElement : elements) {
            actualBugs.add(webElement.getText());
        }
        return actualBugs;
    }


}
