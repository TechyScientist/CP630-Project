package net.johnnyconsole.cp630.project.persistence.impl;

import javax.ejb.Stateful;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.johnnyconsole.cp630.project.persistence.Model;
import net.johnnyconsole.cp630.project.persistence.interfaces.ModelDao;

@Stateful
@Alternative
public class ModelDaoImpl implements ModelDao {
    @PersistenceContext(unitName="model")
    private EntityManager manager;


    @Override
    public Model getModel(String name) {
        try {
            Query query = manager.createNamedQuery("Model.findByName");
            query.setParameter("name", name);
            return (Model) query.getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }
}
