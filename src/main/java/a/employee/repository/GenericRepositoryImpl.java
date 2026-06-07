package a.employee.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import a.employee.model.Attendance;
import a.employee.model.Employee;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
@Repository
public class GenericRepositoryImpl<T> implements BRepository<T>{
    private final EntityManagerFactory emf;
    public GenericRepositoryImpl(EntityManagerFactory emf) { this.emf = emf; }
    @Override
    public void add(T t) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    @Override
    public void update(T t) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    @Override
    public void delete(String id, Class<T> clazz) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            T t = em.find(clazz, id);
            if (t != null) em.remove(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    @Override
    public T findById(String id, Class<T> clazz) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(clazz, id);
        } finally {
            em.close();
        }
    }
    @Override
    public List<T> findAll(Class<T> clazz) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<T> query = em.createQuery("SELECT e FROM " + clazz.getSimpleName() + " e", clazz);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
    public List<Employee> findAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.department LEFT JOIN FETCH e.positions", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public List<Attendance> findAllAttendance(String empId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Attendance> query = em.createQuery("SELECT a FROM Attendance a WHERE a.employeeId = :empId", Attendance.class);
            query.setParameter("empId", empId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}