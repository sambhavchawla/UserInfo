package UserCreationTests;

import API.UserInfoAPI;
import Driver.Driver;
import Driver.SeleniumDriver;
import PageObjects.AllUsers;
import PageObjects.NewUser;
import Property.PropertyReader;
import UserData.UserInfo;
import com.jayway.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Provides common setup and cleanup methods
 * Also provides some common Assertion methods
 */
public class UserCreation {

    protected WebDriver driver;

    protected String URL = PropertyReader.getProperty("host.name");

    protected AllUsers allUsersPage;
    protected NewUser newUserPage;
    protected String NAME = "sam123";
    protected String EMAIL = NAME + "@gmail.com";
    protected String VALIDPASSWORD = "password@123";

    @Before
    public void setup() {
        driver = SeleniumDriver.getWebDriver(Driver.FIREFOX);
        Response response = UserInfoAPI.deleteAllUsers();
        assertEquals("Invalid Delete API Response ", 200, response.getStatusCode());
        assertTrue("Users Not Deleted", UserInfoAPI.getAllUsers().isEmpty());
        driver.get(URL);
        newUserPage = new NewUser(driver);
    }

    @After
    public void cleanUp() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    /**
     * Asserts if all users passed in assertion are created on Page and are available in API Response
     *
     * @param usersExpected
     */
    protected void testUsersCreated(Collection<UserInfo> usersExpected) {
        driver.navigate().refresh();
        if (allUsersPage == null) {
            allUsersPage = newUserPage.navigateToAllUsers();
        }
        assertEquals("Unexpected Number Of Users Visible ", usersExpected.size(), allUsersPage.getAllUsers().size());
        assertEquals("Unexpected Number Of Users in API ", usersExpected.size(), UserInfoAPI.getAllUsers().size());
        for (UserInfo newUser : usersExpected) {
            assertTrue("User Not Visible On All Users Page ", allUsersPage.getAllUsers().contains(newUser));
            assertTrue("User Not Available in API response ", UserInfoAPI.getAllUsers().contains(newUser));
        }
    }

    /**
     * Asserts if all users passed in assertion are NOT created on Page and are NOT available in API Response
     *
     * @param usersNotExpected
     */
    protected void testUsersNotCreated(Collection<UserInfo> usersNotExpected) {
        driver.navigate().refresh();
        if (allUsersPage == null) {
            allUsersPage = newUserPage.navigateToAllUsers();
        }
        for (UserInfo newUser : usersNotExpected) {
            assertTrue("User Visible On All Users Page ", !allUsersPage.getAllUsers().contains(newUser));
            assertTrue("User Available in API response ", !UserInfoAPI.getAllUsers().contains(newUser));
        }
    }
}
