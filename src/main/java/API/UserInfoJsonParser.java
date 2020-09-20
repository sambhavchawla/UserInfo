package API;

import UserData.UserInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides methods to parse Json into User Data
 */
public class UserInfoJsonParser {

    /**
     * Get List of all users returned in JSON response and also verifies if JSON is valid or not
     * @param jsonResponse
     * @return
     */
    public static List<UserInfo> getAllUsers(Response jsonResponse) {
        String json = jsonResponse.asString();
        Assert.assertTrue("Invalid JSON", isJSONValid(json));
        List<JsonObject> resp = stringToJsonList(json);
        List<UserInfo> users = resp.stream().map(UserInfoJsonParser::getUserInfo).collect(Collectors.toList());
        return users;
    }

    /**
     * Converts JSON response to List of smaller objects
     * @param jsonString
     * @return
     */
    private static List<JsonObject> stringToJsonList(String jsonString) {
        Type type = new TypeToken<List<JsonObject>>() {
        }.getType();
        Gson gson = new Gson();
        return gson.fromJson(jsonString, type);
    }

    /**
     * Converts a JSON object into UserInfo
     * @param jsonObject
     * @return
     */
    private static UserInfo getUserInfo(JsonObject jsonObject) {
        Gson gson = new Gson();
        return gson.fromJson(jsonObject.toString(), UserInfo.class);
    }

    /**
     * Returns true if JSON is in valid format, returns false otherwise
     * @param test
     * @return
     */
    private static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
