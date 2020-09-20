package PageObjects;

import UserData.NewUserInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NewUser {

    private WebDriver driver;
    @FindBy(xpath = "//div[@class='page-header']/h1[contains(text(),'New User')]")
    private List<WebElement> newUserPageHeader;
    @FindBy(id = "name")
    private WebElement nameTxtBox;
    @FindBy(id = "email")
    private WebElement emailTxtBox;
    @FindBy(id = "password")
    private WebElement passwordTxtBox;
    @FindBy(id = "confirmationPassword")
    private WebElement confirmPassTxtBox;
    @FindBy(xpath = "//div[@class='form-actions']/a")
    private WebElement allUsersBtn;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitBtn;

    @FindBy(id = "user.name.error")
    private WebElement nameErrorLabel;

    @FindBy(id = "user.email.error")
    private WebElement emailErrorLabel;

    @FindBy(id = "user.password.error")
    private WebElement passwordErrorLabel;
    @FindBy(id = "user.confirmationPassword.error")
    private WebElement confirmPassErrorLabel;

    public NewUser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    WebElement getNameErrorLabel() {
        return nameErrorLabel;
    }

    WebElement getEmailErrorLabel() {
        return emailErrorLabel;
    }

    WebElement getPasswordErrorLabel() {
        return passwordErrorLabel;
    }

    WebElement getConfirmPassErrorLabel() {
        return confirmPassErrorLabel;
    }

    /**
     * Creates New User based on NewUserInfo object passed
     * @param newUserInfo
     */
    private void createUser(NewUserInfo newUserInfo) {
        nameTxtBox.sendKeys(newUserInfo.getName());
        emailTxtBox.sendKeys(newUserInfo.getEmail());
        passwordTxtBox.sendKeys(newUserInfo.getPassword());
        confirmPassTxtBox.sendKeys(newUserInfo.getComfirmPassword());
        submitBtn.click();
    }

    /**
     * Creates new user and retuns initialised expected page passed.Can be used in scenario where multiple
     * combinations of next page are possible
     * @param newUserInfo
     * @param expectedPage
     * @param <T>
     * @return
     */
    private <T> T createUserAndNavigate(NewUserInfo newUserInfo, Class<T> expectedPage) {
        createUser(newUserInfo);
        return PageFactory.initElements(driver, expectedPage);
    }

    /**
     * To be used when All New User details are valid and AllUsers page is expected
     * @param newUserInfo
     * @return
     */
    public AllUsers validUserCreation(NewUserInfo newUserInfo) {
        return createUserAndNavigate(newUserInfo, AllUsers.class);
    }

    /**
     * To be used when we have to enter invalid details.
     * Returns true if driver remains on same page, false if next page is loaded
     * @param newUserInfo
     * @return
     */
    public boolean invalidUserCreation(NewUserInfo newUserInfo) {
        createUser(newUserInfo);
        if (newUserPageHeader.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Returns object containing all Error Labels
     * @return
     */
    public ErrorLabels getErrorMessages(){
        return new ErrorLabels(this);
    }

    /**
     * Navigates to All Users page and returns its initialised object
     * @return
     */
    public AllUsers navigateToAllUsers() {
        allUsersBtn.click();
        return PageFactory.initElements(driver, AllUsers.class);
    }

    /**
     * Creates new user and navigates again to new user page.
     * To be used when we have to create multiple users
     * @param newUserInfo
     * @return
     */
    public NewUser createUserAndReturnToNewUser(NewUserInfo newUserInfo) {
        createUser(newUserInfo);
        AllUsers allUsers = PageFactory.initElements(driver, AllUsers.class);
        return allUsers.navigateToNewUser();
    }

}
