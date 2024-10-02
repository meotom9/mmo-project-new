package mmo.project.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import mmo.project.model.MmoUserAgent;
import mmo.project.utils.HibernateUtil;

import java.util.ArrayList;


public class MmoUserAgentDAO {

    public ArrayList<MmoUserAgent> getAllUsers() {
        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<MmoUserAgent> query = entityManager.createNamedQuery("MmoUserAgent.findAll", MmoUserAgent.class);
            return (ArrayList<MmoUserAgent>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
