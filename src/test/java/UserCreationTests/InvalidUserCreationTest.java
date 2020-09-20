package UserCreationTests;

import API.UserInfoAPI;
import PageObjects.ErrorLabels;
import UserData.NewUserInfo;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Tests scenarios in which user should not be created and error message should be displayed
 */
public class InvalidUserCreationTest extends UserCreation {


    /**
     * Tries to create user with repeated name and email
     */
    @Test
    public void repeatNameAndEmailCreationTest() {
        NewUserInfo newUserInfo = new NewUserInfo(NAME, EMAIL, VALIDPASSWORD, VALIDPASSWORD);
        newUserPage = newUserPage.createUserAndReturnToNewUser(newUserInfo);
        assertTrue("Incorrect Transition ", newUserPage.invalidUserCreation(newUserInfo));
        ErrorLabels errorLabels = newUserPage.getErrorMessages();
        assertEquals("Invalid Error Message displayed when user created with repeated NAME ", "Must be unique", errorLabels.getNameErrorMsg());
        //Validates that client has been created only once
        testUsersCreated(Collections.singleton(newUserInfo));
    }


    /**
     * Tries to create user with repeated Email only
     */
    @Test
    public void repeatEmailOnlyCreationTest() {
        NewUserInfo newUserInfo = new NewUserInfo(NAME, EMAIL, VALIDPASSWORD, VALIDPASSWORD);
        newUserPage = newUserPage.createUserAndReturnToNewUser(newUserInfo);
        NewUserInfo newUserInfoEmailRepeat = new NewUserInfo(NAME + "diff", EMAIL, VALIDPASSWORD, VALIDPASSWORD);
        assertTrue("Incorrect Transition ", newUserPage.invalidUserCreation(newUserInfoEmailRepeat));
        ErrorLabels errorLabels = newUserPage.getErrorMessages();
        assertEquals("Invalid Error Message displayed when user created with repeated EMAIL ", "Must be unique", errorLabels.getEmailErrorMsg());
        testUsersNotCreated(Collections.singleton(newUserInfoEmailRepeat));
    }

    /**
     * Tries to create user with invalid Email
     */
    @Test
    public void invalidEmailTest() {
        NewUserInfo newUserInfo = new NewUserInfo(NAME, "abc", VALIDPASSWORD, VALIDPASSWORD);
        assertTrue("Incorrect Transition ", newUserPage.invalidUserCreation(newUserInfo));
        ErrorLabels errorLabels = newUserPage.getErrorMessages();
        assertEquals("Invalid Error Message displayed when user created with repeated EMAIL ", "Invalid email address", errorLabels.getEmailErrorMsg());
        testUsersNotCreated(Collections.singleton(newUserInfo));
    }

    /**
     * Tries to create user with all fields empty
     */
    @Test
    public void allEmptyFieldsUserCreationTest() {
        NewUserInfo newUserInfo = new NewUserInfo("", "", "", "");
        assertTrue("Incorrect Transition ", newUserPage.invalidUserCreation(newUserInfo));
        ErrorLabels errorLabels = newUserPage.getErrorMessages();
        assertEquals("All Fields Blank, Invalid Error message on Name", "Required", errorLabels.getNameErrorMsg());
        assertEquals("All Fields Blank, Invalid Error message on Email", "Required", errorLabels.getEmailErrorMsg());
        assertEquals("All Fields Blank, Invalid Error message on Password", "Required", errorLabels.getPasswordErrorMsg());
        testUsersNotCreated(Collections.singleton(newUserInfo));
    }

    /**
     * Tries to create user with all fields empty
     */
    @Test
    public void emptyPasswordUserCreationTest() {
        NewUserInfo newUserInfo = new NewUserInfo(NAME, EMAIL, "", VALIDPASSWORD);
        assertTrue("Incorrect Transition ", newUserPage.invalidUserCreation(newUserInfo));
        ErrorLabels errorLabels = newUserPage.getErrorMessages();
        assertEquals("Password Blank Blank, Invalid Error message on Password", "Required", errorLabels.getPasswordErrorMsg());
        testUsersNotCreated(Collections.singleton(newUserInfo));
    }

    /**
     * Tries to create user with different once with lower case then with upper case name and email
     */
    @Test
    public void sameUserDifferentCaseTest() {
        NewUserInfo newUserInfo = new NewUserInfo(NAME, EMAIL, VALIDPASSWORD, VALIDPASSWORD);
        newUserPage = newUserPage.createUserAndReturnToNewUser(newUserInfo);
        NewUserInfo newUserInfoEmailRepeat = new NewUserInfo(NAME.toUpperCase(), EMAIL.toUpperCase(), VALIDPASSWORD, VALIDPASSWORD);
        assertTrue("Incorrect Transition ", newUserPage.invalidUserCreation(newUserInfoEmailRepeat));
        ErrorLabels errorLabels = newUserPage.getErrorMessages();
        assertFalse("No Error message displayed & new User Created with same username and EMAIL in upper case ", UserInfoAPI.getAllUsers().contains(newUserInfo));
        if (errorLabels.getPasswordErrorMsg().isEmpty() || errorLabels.getConfirmPassErrorMsg().isEmpty()) {
            fail("No Error message password shorter than 6 characters");
        }
    }

    /**
     * Tries to create user with repeat Name only
     */
    @Test
    public void repeatNameOnlyCreationTest() {
        NewUserInfo newUserInfo = new NewUserInfo(NAME, EMAIL, VALIDPASSWORD, VALIDPASSWORD);
        newUserPage = newUserPage.createUserAndReturnToNewUser(newUserInfo);
        NewUserInfo newUserInfoEmailRepeat = new NewUserInfo(NAME, "diff" + EMAIL, VALIDPASSWORD, VALIDPASSWORD);
        assertTrue("Incorrect Transition ", newUserPage.invalidUserCreation(newUserInfoEmailRepeat));
        ErrorLabels errorLabels = newUserPage.getErrorMessages();
        assertEquals("Invalid Error Message displayed when user created with repeated EMAIL ", "Must be unique", errorLabels.getNameErrorMsg());
        testUsersNotCreated(Collections.singleton(newUserInfoEmailRepeat));
    }

    /**
     * Tries to create user with password less than 6 characters
     */
    @Test
    public void shortPasswordCreationTest() {
        NewUserInfo newUserInfo = new NewUserInfo(NAME, EMAIL, "123", "123");
        assertTrue("Incorrect Transition ", newUserPage.invalidUserCreation(newUserInfo));
        ErrorLabels errorLabels = newUserPage.getErrorMessages();
        assertFalse("No Error message displayed & new User Created with password shorter than 6 characters ", UserInfoAPI.getAllUsers().contains(newUserInfo));
        if (errorLabels.getPasswordErrorMsg().isEmpty() || errorLabels.getConfirmPassErrorMsg().isEmpty()) {
            fail("No Error message password shorter than 6 characters");
        }
    }

    /**
     * Tries to create user with password and confirm password different
     */
    @Test
    public void confirmAndPasswordDifferent() {
        NewUserInfo newUserInfo = new NewUserInfo(NAME, EMAIL, VALIDPASSWORD, VALIDPASSWORD + "diff");
        assertTrue("Incorrect Transition ", newUserPage.invalidUserCreation(newUserInfo));
        ErrorLabels errorLabels = newUserPage.getErrorMessages();
        assertEquals("Invalid Error Message displayed when user password and confirm password different ", "passwords are not the same", errorLabels.getConfirmPassErrorMsg());
        testUsersNotCreated(Collections.singleton(newUserInfo));
    }

}
