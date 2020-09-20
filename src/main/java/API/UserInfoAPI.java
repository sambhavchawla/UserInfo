package API;

import Property.PropertyReader;
import UserData.UserInfo;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import java.util.List;

import static com.jayway.restassured.RestAssured.baseURI;

/**
 * Provides Functionality to access REST Services
 */
public class UserInfoAPI {

    /**
     * Returns List of Users currently in the system received by API
     *
     * @return List of UserInfo
     */
    public static List<UserInfo> getAllUsers() {
        baseURI = PropertyReader.getProperty("host.name");
        Response getResponse = RestAssured.get(PropertyReader.getProperty("all.users"));
        return UserInfoJsonParser.getAllUsers(getResponse);
    }

    /**
     * Returns Response after deleting all Users via API call
     *
     * @return Rest Assured Response
     */
    public static Response deleteAllUsers() {
        baseURI = PropertyReader.getProperty("host.name");
        return RestAssured.delete(PropertyReader.getProperty("delete.users"));
    }
}
