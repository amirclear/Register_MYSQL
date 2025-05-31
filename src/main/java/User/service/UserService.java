package User.service;
import User.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import Hibernate.HibernateUtil;

import java.util.Scanner;

public class UserService {

    public void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (password.length() < 8) {
            System.out.println("Weak password");
            return;
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        User existingUser = session
                .createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", email)
                .uniqueResult();

        if (existingUser != null) {
            System.out.println("An account with this email already exists");
            session.close();
            return;
        }

        User newUser = new User(firstName, lastName, age, email, password);
        session.persist(newUser);
        tx.commit();
        session.close();
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Session session = HibernateUtil.getSessionFactory().openSession();

        User user = session
                .createQuery("FROM User WHERE email = :email", User.class)
                .setParameter("email", email)
                .uniqueResult();

        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Invalid email or password");
            session.close();
            return;
        }

        System.out.println("Welcome, " + user.getFullName() + "!");
        session.close();
    }
}
