package UserCreationTests;

import API.UserInfoAPI;
import UserData.NewUserInfo;
import UserData.UserInfo;
import com.jayway.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests scenarios in which user should be created
 */
public class ValidUserCreationTest extends UserCreation {

    /**
     * Test to assert if user is created on correct details
     */
    @Test
    public void singleUserCreationTest() {
        NewUserInfo newUserInfo = new NewUserInfo(NAME, EMAIL, VALIDPASSWORD, VALIDPASSWORD);
        allUsersPage = newUserPage.validUserCreation(newUserInfo);
        testUsersCreated(Collections.singleton(newUserInfo));
    }

    /**
     * Asserts if multiple users can be created all with unique info
     */
    @Test
    public void createMultipleUsersTest() {
        int users = 5;
        List<UserInfo> userInfos = new LinkedList<>();
        for (int i = 1; i <= users; i++) {
            NewUserInfo newUserInfo = new NewUserInfo("sam" + i, "sam" + i + "@gmail.com", VALIDPASSWORD, VALIDPASSWORD);
            newUserPage = newUserPage.createUserAndReturnToNewUser(newUserInfo);
            userInfos.add(newUserInfo);
        }
        allUsersPage = newUserPage.navigateToAllUsers();
        testUsersCreated(userInfos);
    }

    /**
     * Multiple users are created and deleted from API
     */
    @Test
    public void deleteAPITest() {
        createMultipleUsersTest();
        Response response = UserInfoAPI.deleteAllUsers();
        assertEquals("Invalid Delete API Response ", 200, response.getStatusCode());
        testUsersCreated(Collections.emptyList());
    }
}
