package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Applied to repositories and DAOs implementations
// Provides translations of any JDBC related exceptions
@Repository
public class StudentDAOImpl implements StudentDAO {

    private final EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        this.entityManager.persist(student);
    }

    @Override
    // We do not use @Transactional here because it is just a query, not an update
    // A query is a question or inquiry about a set of data.
    public Optional<Student> findById(Integer id) {
        // find() is used to find an entity in the database using primary key
        return Optional.ofNullable(this.entityManager.find(Student.class, id));
    }

    @Override
    public List<Student> findAll() {
        // All JPQL syntax is based on entity name and entity fields
        TypedQuery<Student> query = this.entityManager.createQuery("FROM Student s ORDER BY s.lastName ASC",
            Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = this.entityManager.createQuery("FROM Student s WHERE s.lastName = :lastName",
            Student.class).setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        this.entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Optional<Student> student = this.findById(id);
        student.ifPresent(this.entityManager::remove);
    }

    @Override
    @Transactional
    public int deleteAll() {
        // Method name executeUpdate is a generic term. We are "modifying" the database
        return this.entityManager.createQuery("DELETE FROM Student").executeUpdate();
    }
}

