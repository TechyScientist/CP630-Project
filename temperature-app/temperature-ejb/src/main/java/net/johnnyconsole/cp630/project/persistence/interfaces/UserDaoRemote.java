package net.johnnyconsole.cp630.project.persistence.interfaces;

import net.johnnyconsole.cp630.project.persistence.User;

import javax.ejb.Remote;

@Remote
public interface UserDaoRemote {

    User getUser(String username);
    boolean verifyUser(String username, String passwordPlainText);
}
