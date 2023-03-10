package registration;

import java.io.Serializable;

/**
 *
 * @author TrongPS
 */
public class registrationDTO implements Serializable {

    private String username;
    private String password;
    private String fullname;
    private boolean isAdmin;

    public registrationDTO() {
    }

    public registrationDTO(String username, String password, String fullname, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return username + " " + password + " " + fullname + " " + isAdmin;
    }

    
}
