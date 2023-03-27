package guru.springframework.services.jpaservices;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import guru.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Created by jt on 12/14/15.
 */
@Service
@Profile("jpadao")
public class UserServiceJpaDaoImpl extends AbstractJpaDaoService implements UserService {

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<?> listAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("from User", User.class).getResultList();
        }


    }

    @Override
    public User getById(Integer id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(User.class, id);
        }
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            if(domainObject.getPassword() != null){
                domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
            }

            User saveduser = em.merge(domainObject);
            em.getTransaction().commit();

            return saveduser;
        }

    }

    @Override
    public void delete(Integer id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.remove(em.find(User.class, id));
            em.getTransaction().commit();
        }

    }

    @Override
    public User findByUsername(String userName) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("from User where userName = :userName", User.class)
                    .setParameter("userName", userName)
                    .getSingleResult();
        }

    }
}
