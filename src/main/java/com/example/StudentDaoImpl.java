package com.example;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Transactional
public class StudentDaoImpl implements GenericDao<Student, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student findByEmail(String email) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<Student> findAll() {
        return entityManager.createQuery("SELECT s FROM Student s", Student.class).getResultList();
    }

    @Override
    public Student update(Student student) {
        return entityManager.merge(student);
    }

    @Override
    public boolean deleteById(Long id) {
        Student student = findById(id);
        if (student != null) {
            entityManager.remove(student);
            return true;
        }
        return false;
    }
}
