package pageobjects;

public class DataUser {
    private String email;
    private String password;
    private String name;

    public DataUser(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
}
