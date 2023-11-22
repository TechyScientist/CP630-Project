package net.johnnyconsole.cp630.project.persistence.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import net.johnnyconsole.cp630.project.persistence.User;
import net.johnnyconsole.cp630.project.persistence.interfaces.UserDao;
import net.johnnyconsole.cp630.project.persistence.interfaces.UserDaoRemote;

import javax.ejb.RemoteHome;
import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
@Alternative
public class UserDaoImpl implements UserDao, UserDaoRemote {

    @PersistenceContext(unitName="user")
    private EntityManager manager;

    @Override
    public User getUser(String username) {
        Query query = manager.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

    public boolean addUser(User user) {
        try {
            manager.persist(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

        public boolean removeUser(User user, String myUsername) {
            try {
                //From https://stackoverflow.com/questions/17027398/java-lang-illegalargumentexception-removing-a-detached-instance-com-test-user5
                //EntityManager can only remove entities currently in its context - if the user isn't in the manager's context, merge it in to make it managed.
                if(user.getUsername().equals(myUsername)) return false;
                manager.remove(manager.contains(user) ? user : manager.merge(user));
                return true;
            } catch (Exception e) {
                System.out.println(e);
                return false;
            }
        }

    @Override
    public boolean verifyUser(String username, String passwordPlainText) {
        User user = getUser(username);
        return BCrypt.verifyer().verify(passwordPlainText.getBytes(), user.getPassword().getBytes()).verified;
    }
}
