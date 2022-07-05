package org.example.dao;

import org.example.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    /**Hibernate*/

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true) // если не вносим изменения
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }
    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }
    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person personToBeUpdated = session.get(Person.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());

    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(session.get(Person.class, id));
    }


    /**JdbcTemplate*/
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
////        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Optional<Person> show(String email) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE email=?", new Object[]{email},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
//                        new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(name, age, email, address) VALUES(?,?,?,?)",
//                person.getName(), person.getAge(), person.getEmail(), person.getAddress());
//    }
//
//    public void update(int id, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE Person Set name=?, age=?, email=? address=? WHERE id=?",
//                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), updatedPerson.getAddress(), id);
//
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
//    }
//
//    public void testMultipleUpdate() {
//        List<Person> people = create1000People();
//
//        long before = System.currentTimeMillis();
//        for (Person person : people) {
//            jdbcTemplate.update("INSERT INTO Person(name, age, email) VALUES(?,?,?,?)",
//                    person.getName(), person.getAge(), person.getEmail(), person.getAddress());
//        }
//        long after = System.currentTimeMillis();
//        System.out.println("Time without: " + (after - before));
//    }
//
//    public void testBatchUpdate() {
//        List<Person> people = create1000People();
//        long before = System.currentTimeMillis();
//
//        jdbcTemplate.batchUpdate("INSERT INTO Person(name, age, email, address) VALUES(?,?,?,?)", new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
//                preparedStatement.setString(1, people.get(i).getName());
//                preparedStatement.setInt(2, people.get(i).getAge());
//                preparedStatement.setString(3, people.get(i).getEmail());
//                preparedStatement.setString(4, people.get(i).getAddress());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return people.size();
//            }
//        });
//
//        long after = System.currentTimeMillis();
//        System.out.println("Time with: " + (after - before));
//    }
//
//    private List<Person> create1000People() {
//        List<Person> people = new ArrayList<>();
//
//        for (int i = 0; i < 1000; i++) {
//            people.add(new Person(i, "Name " + i, 30, "test" + i + "@gmail.com", "some address"));
//        }
//        return people;
//    }


}
