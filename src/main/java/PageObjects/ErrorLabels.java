package PageObjects;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Class to access all Error Labels when invalid data is entered on New User Page
 */
public class ErrorLabels {
    private String nameErrorMsg;
    private String emailErrorMsg;
    private String passwordErrorMsg;
    private String confirmPassErrorMsg;

    public ErrorLabels(NewUser newUser) {
        this.setNameErrorMsg(newUser.getNameErrorLabel().getText());
        this.setEmailErrorMsg(newUser.getEmailErrorLabel().getText());
        this.setPasswordErrorMsg(newUser.getPasswordErrorLabel().getText());
        this.setConfirmPassErrorMsg(newUser.getConfirmPassErrorLabel().getText());
    }

    public String getNameErrorMsg() {
        return nameErrorMsg;
    }

    public void setNameErrorMsg(String nameErrorMsg) {
        this.nameErrorMsg = nameErrorMsg;
    }

    public String getEmailErrorMsg() {
        return emailErrorMsg;
    }

    public void setEmailErrorMsg(String emailErrorMsg) {
        this.emailErrorMsg = emailErrorMsg;
    }

    public String getPasswordErrorMsg() {
        return passwordErrorMsg;
    }

    public void setPasswordErrorMsg(String passwordErrorMsg) {
        this.passwordErrorMsg = passwordErrorMsg;
    }

    public String getConfirmPassErrorMsg() {
        return confirmPassErrorMsg;
    }

    public void setConfirmPassErrorMsg(String confirmPassErrorMsg) {
        this.confirmPassErrorMsg = confirmPassErrorMsg;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
