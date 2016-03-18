package ssteinkellner;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author SSteinkellner
 * @version 2016.03.11
 */
@Entity
public class User implements Serializable {
	
	@Id
    @Size(max = 100)
    private String email;

    @Size(max = 100)
    private String username;

    @NotEmpty
    private String password;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    protected User() {}

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
