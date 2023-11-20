package net.johnnyconsole.cp630.project.persistence;

import javax.persistence.*;

@Entity
@Table(name="cons3250_project_user")
@NamedQueries({
        @NamedQuery(name="User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
})
public class User {

    @Id
    private String username;
    private String name, password;
    private int accessLevel;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return "User[username=" + username + ", name=" + name + ", accessLevel=" + accessLevel + "]";
    }
}
