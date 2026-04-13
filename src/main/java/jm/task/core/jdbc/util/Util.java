package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "4815162342";

    private static SessionFactory sessionFactory;

    public static Connection getConnection () {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ОШИБКА ПОДКЛЮЧЕНИЯ К БД" + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.setProperty("hibernate.connection.url", URL);
                properties.setProperty("hibernate.connection.username", USERNAME);
                properties.setProperty("hibernate.connection.password", PASSWORD);
                properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
                properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                properties.setProperty("hibernate.show_sql", "true");
                properties.setProperty("hibernate.format_sql", "true");
                properties.setProperty("hibernate.hbm2ddl.auto", "validate");

                sessionFactory = new Configuration()
                        .addProperties(properties)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (Exception e) {
                System.out.println("ОШИБКА СОЗДАНИЯ SESSION FACTORY: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }



    public static void close (Connection connection) throws SQLException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        }
    }
}
