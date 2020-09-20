package PageObjects;

import UserData.UserInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Page Object class for All Users page
 */
public class AllUsers {

    private final String tableXpath = "//table[@id='users']/tbody/tr";
    @FindBy(xpath = tableXpath + "/td[1]")
    private List<WebElement> names;

    @FindBy(xpath = tableXpath + "/td[2]")
    private List<WebElement> emails;

    @FindBy(xpath = tableXpath + "/td[3]")
    private List<WebElement> passwords;

    @FindBy(className = "page-header")
    private WebElement pageHeader;

    @FindBy(xpath = tableXpath + "/td[contains(text(),'No Users')]")
    private List<WebElement> noUsersLabel;

    @FindBy(xpath = "//a[@class='btn btn-default']")
    private WebElement newUsersBtn;

    private WebDriver driver;

    public AllUsers(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Returns a list of All Users displayed on the page
     * @return
     */
    public List<UserInfo> getAllUsers() {
        if (!noUsersLabel.isEmpty())
            return Collections.emptyList();
        List<UserInfo> allUsers = new LinkedList<>();
        for (int index = 0; index < names.size(); index++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName(names.get(index).getText());
            userInfo.setEmail(emails.get(index).getText());
            userInfo.setPassword(passwords.get(index).getText());
            allUsers.add(userInfo);
        }
        return allUsers;
    }

    /**
     * Navigates to new user page and returns its object
     * @return
     */
    public NewUser navigateToNewUser() {
        newUsersBtn.click();
        return PageFactory.initElements(driver, NewUser.class);
    }

}
