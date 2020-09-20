package UserData;

import com.google.common.base.Strings;

import static java.util.Objects.hash;

/**
 * Class to store Information displayed on AllUsers page
 */
public class UserInfo {

    private String name;
    private String email;
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            this.name = "";
        } else {
            this.name = name;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (Strings.isNullOrEmpty(email)) {
            this.email = "";
        } else {
            this.email = email;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (Strings.isNullOrEmpty(password)) {
            this.password = "";
        } else {
            this.password = password;
        }
    }

    public int hashCode() {
        return hash(this.getName() + this.getEmail() + this.getPassword());
    }

    public boolean equals(Object obj) {
        boolean flag = false;
        UserInfo userInfo = (UserInfo) obj;
        if (this.getName().equals(userInfo.getName()) && this.getEmail().equals(userInfo.getEmail()) && this.getPassword().equals(userInfo.getPassword()))
            flag = true;
        return flag;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", Email: " + getEmail() + ", Password: " + getPassword();
    }
}
