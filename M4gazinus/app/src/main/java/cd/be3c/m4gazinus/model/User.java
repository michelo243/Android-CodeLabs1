package cd.be3c.m4gazinus.model;

/**
 * @author Michelo
 * @version 0.1.0
 * @date 01/06/2017
 */
public class User {

    private String name;
    private  String email;
    private String password;

    public User() {
        //null constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
