package net.johnnyconsole.cp630.project.persistence.interfaces;

import net.johnnyconsole.cp630.project.persistence.User;

public interface UserDao {

    User getUser(String username);
    boolean verifyUser(String username, String passwordPlainText);
}
