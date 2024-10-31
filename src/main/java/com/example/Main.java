package com.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hillel-persistence-unit");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Пример работы с сущностями
            Student student = new Student();
            student.setFirstName("John");
            student.setLastName("Doe");
            student.setEmail("john.doe@example.com");

            em.persist(student);

            em.getTransaction().commit();
            System.out.println("Student saved successfully!");
        } finally {
            em.close();
            emf.close();
        }
    }
}
