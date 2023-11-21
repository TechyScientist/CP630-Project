package net.johnnyconsole.cp630.project.persistence.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import net.johnnyconsole.cp630.project.persistence.User;
import net.johnnyconsole.cp630.project.persistence.interfaces.UserDao;

import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
@Alternative
public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName="user")
    private EntityManager manager;

    @Override
    public User getUser(String username) {
        Query query = manager.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

    @Override
    public boolean verifyUser(String username, String passwordPlainText) {
        User user = getUser(username);
        return BCrypt.verifyer().verify(passwordPlainText.getBytes(), user.getPassword().getBytes()).verified;
    }
}
