package br.com.curso.model;

/**
 *
 * @author Talita
 */
public class Profile {
    
    private int id;
    private String username;
    private String email;
    private String passcode;

    public Profile() {
        this.id = 0;
        this.username = "";
        this.email = "";
        this.passcode = "";
    }

    public Profile(int id, String username, String email, String passcode) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passcode = passcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}
