package Controller;

/**
 *
 * @author TrongPS
 */
public class ERROR {

    String accountError;
    String passwordError;
    String confirmPasswordError;
    String fullnameError;

    public ERROR() {
        accountError = "";
        passwordError = confirmPasswordError = fullnameError = "";
    }

    public ERROR(String accountError, String passwordError, String confirmPasswordError, String fullnameError) {
        this.accountError = accountError;
        this.passwordError = passwordError;
        this.confirmPasswordError = confirmPasswordError;
        this.fullnameError = fullnameError;
    }

    public String getAccountError() {
        return accountError;
    }

    public void setAccountError(String accountError) {
        this.accountError = accountError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }

    public String getFullnameError() {
        return fullnameError;
    }

    public void setFullnameError(String fullnameError) {
        this.fullnameError = fullnameError;
    }

}
