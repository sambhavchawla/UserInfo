package UserData;

import com.google.common.base.Strings;

/**
 * Class to store information needed to be passed when creating new user
 */
public class NewUserInfo extends UserInfo {

    private String comfirmPassword;

    public NewUserInfo(String name, String email, String password, String confirmPassword) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setComfirmPassword(confirmPassword);
    }

    public String getComfirmPassword() {
        return comfirmPassword;
    }

    public void setComfirmPassword(String comfirmPassword) {
        if (Strings.isNullOrEmpty(comfirmPassword)) {
            this.comfirmPassword = "";
        } else {
            this.comfirmPassword = comfirmPassword;
        }
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Email: " + getEmail() + ", Password: " + getPassword() + ", Confirm password: "
                + getComfirmPassword();
    }

}
