package Infrastructure.pages;


import Infrastructure.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {
    @FindBy(xpath = "//span[@id='ext-gen1087']")
    private WebElement addBugButton;
    @FindBy(xpath = "//table[@class='x-grid-table x-grid-table-resizer']")
    private  WebElement bugTable;
    @FindBy(xpath = "//span[@id='ext-gen1092']")
    private  WebElement editBugButton;
    @FindBy(xpath = "//span[@id='ext-gen1097']")
    private  WebElement applyBugButton;
    @FindBy(xpath = "//span[@id='ext-gen1102']")
    private  WebElement deleteBugButton;
    @FindBy(xpath = "//div[@id='button-1010']")
    private  WebElement confirmationButtonYes;
    @FindBy(xpath = "//div[@id='button-1047']")
    private  WebElement addBugFromForm;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement inputBugField;
    @FindBy(xpath = "//textarea[@name='notes']")
    private WebElement inputNotesField;
    @FindBy(xpath = "//button[@data-qtip='OK']")
    private WebElement confirmBugButton;





    private static final String NEW_NAME_BUG = "OriekhovNewBug";
    private static final String NEW_NOTES_BUG = "Value-1";
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainPage openMainPage(String url){
        driver.get(url);
        return this;
    }



    public String getFirstBug() {
        List<String> bugList = getBugTable().getNameBugFromTable();
        for (String bug: bugList){
            if (bug.equals("First Bug")){
                return "First Bug";
            }
        }
        return null;
    }


    public void addNewBug(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(addBugButton));
        addBugButton.click();
        WaitUtils.waitABit(200);
        Actions ac = new Actions(driver);
        ac.sendKeys(NEW_NAME_BUG).perform();
        ac.sendKeys(Keys.TAB).perform();
        WaitUtils.waitABit(200);
        ac.sendKeys(NEW_NOTES_BUG).perform();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(editBugButton));
        editBugButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(applyBugButton));
        applyBugButton.click();
        WaitUtils.waitForLoad(driver);
    }

    public String getNewBug(){
        List<String> bugList = getBugTable().getNameBugFromTable();
        for (String bug: bugList){
            if (bug.equals(NEW_NAME_BUG)){
                return "Bug is exist";
            }
        }
        return "Bug isn't exist";
    }

    public void deleteNewBug(){
        driver.navigate(). refresh();
        Actions ac = new Actions(driver);
        ac.doubleClick(driver.findElement(By.xpath("//div[contains(text(), '"+NEW_NAME_BUG+"')]"))).perform();
        WaitUtils.waitABit(500);
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(deleteBugButton));
        deleteBugButton.click();
        WaitUtils.waitABit(500);
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(confirmationButtonYes));
        confirmationButtonYes.click();
        WaitUtils.waitForLoad(driver);
    }


    public BugsTable getBugTable(){
        return new BugsTable(bugTable, driver);
    }

    public void addBugFromForm(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(addBugFromForm));
        addBugFromForm.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(inputBugField));
        inputBugField.sendKeys(NEW_NAME_BUG);

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(inputNotesField));
        inputNotesField.sendKeys(NEW_NOTES_BUG);

        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(confirmBugButton));

        confirmBugButton.click();

    }

}
