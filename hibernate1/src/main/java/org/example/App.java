package org.example;


import org.example.model.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();


        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
//
//            Person person = session.get(Person.class, 3);
//            System.out.println(person);
//
//            List<Item> items = person.getItems();
//            System.out.println(items);

//            Item item = session.get(Item.class, 5);
//            System.out.println(item);
//
//            System.out.println(item.getOwner());
//

            // Новый товар для существующего человека
//            Person person = session.get(Person.class, 2);
//            Item newItem = new Item("Item from hibernate", person);
//
//            person.getItems().add(newItem);
//            session.save(newItem);
            // Новый челове, новый товар
//            Person newPerson = new Person("Test Person", 30);
//            Item newItem2 = new Item("Item for test Person", newPerson);

            // Чтобы было правильное состояние в кэше
//            newPerson.setItems(new ArrayList<Item>(Collections.singletonList(newItem2)));
//
//            session.save(newPerson);
//            session.save(newItem2);

            // Удаляем товар у человека
//            Person person = session.get(Person.class, 3);
//            List<Item> items = person.getItems();
//
//            for (Item item : items) {
//                session.remove(item);
//            }
            // Чтобы было правильное состояние в кэше
//            person.getItems().clear();

            // Удаляем человека
//            Person person = session.get(Person.class, 2);
//            session.remove(person);
//            // Чтобы было правильное состояние в кэше
//            person.getItems().forEach(x-> x.setOwner(null));

            // Пробуем поменять владельца товара
//            Person person = session.get(Person.class, 4);
//            Item item = session.get(Item.class, 1);

            //Чтобы было правильное состояние в кэше
//            item.getOwner().getItems().remove(item);
//
//            item.setOwner(person);
//            person.getItems().add(item);


//            /**
//             Каскадирование
//             */
//
//            Person person = new Person("Test cascading2", 49);
//            Item item1 = new Item("Item1");
//            Item item2 = new Item("Item2");
//            Item item3 = new Item("Item3");
//
//            person.addItem(item1);
//            person.addItem(item2);
//            person.addItem(item3);
//
//            session.save(person);
////          or  session.persist(person);


            /** OneToOne*/
            // Новый челове, новый пасспорт
//            Person person = new Person("Test oneToOne", 50);
//            Passport passport = new Passport(12345);
//            person.setPassport(passport);
//            session.save(person);

            // Существующий человек, меняем пасспорт
//            Person person = session.get(Person.class, 10);
//            person.getPassport().setPassportNumber(7777);

            // Удаляем
//            Person person = session.get(Person.class, 10);
//            session.remove(person);

            /**ManyToMany*/

//            Movie movie = new Movie("Movie1", 1934);
//            Actor actor1 = new Actor("Actor1", 30);
//            Actor actor2 = new Actor("Actor2", 30);
//
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));
//
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);

            // Выводим список актеров из опред фильма
//            Movie movie = session.get(Movie.class, 1);
//            System.out.println(movie.getActors());

            // Новый фильм для существующего актера

//            Movie movie2 = new Movie("Movie2", 2000);
//            Actor actor = session.get(Actor.class, 1);
//
//            actor.getMovies().add(movie2);
//            movie2.setActors(new ArrayList<>(List.of(actor)));
//
//
//            session.save(movie2);

            // Удалим фильм у существующего актера

//            Actor actor = session.get(Actor.class, 2);
//            System.out.println(actor.getMovies());
//
//            Movie movieToRemove = actor.getMovies().get(0);
//
//            actor.getMovies().remove(0);
//            movieToRemove.getActors().remove(actor); // Использует хэш код и иквалс


            /**Lazy and Eager Loading*/


            Person person = session.get(Person.class, 1);
            System.out.println("Получили человека");

            // Получим связаные сущности (Lazy)
//            System.out.println(person.getItems());

//            Hibernate.initialize(person.getItems()); // подгружаем явно
            // Eager (не лениво)

//            Item item = session.get(Item.class, 1);
//            System.out.println("Получили товар");
//            System.out.println(item.getOwner());
//
            session.getTransaction().commit();
            //session.close()

            System.out.println("Сессия закончилась!");

            // Вне сесси можем получить товары если в Person  @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)

            // Открываем ссесию и транзакцию еще раз (можем сдеалть в любом месте в коде)
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Внутри второй ссесии");

            person = (Person) session.merge(person); // для 2 способа можно убрать
//            Hibernate.initialize(person.getItems()); // 1 способ
            List<Item> items = session.createQuery("select i from Item i where i.owner.id=:personId", Item.class)
                    .setParameter("personId", person.getId()).getResultList();     // 2 способ

            System.out.println(items);

            session.getTransaction().commit();

            System.out.println("Вне второй  ссесий");

            // Работает так как товары подгрузились во второй сессии в 1 способе
//            System.out.println(person.getItems());

        } finally {
            sessionFactory.close();
        }
    }
}