package org.example.config;

import org.example.models.Book;
import org.example.models.Person;
import org.example.services.PeopleService;
import org.hibernate.cfg.Configuration;
import org.example.services.BooksService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;


public class Test {



    public static void main(String[] args) {

        Configuration configuration = new Configuration().addAnnotatedClass(Book.class).addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Book b1 = session.get(Book.class, 1);
            Date d1 = new Date();
            b1.setReturnDate(new Date(d1.getTime() - (22 * 24 * 60 * 60 * 1000)));
            System.out.println(b1.isBookOutOfDate());


            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }


        Date d1 = new Date();
        Date d2 = new Date((d1.getTime() + 20 * 24 * 60 * 60 * 1000));
        System.out.println(d2.getTime());

        System.out.println();
    }
}
