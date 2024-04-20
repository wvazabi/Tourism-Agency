package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class Db {

    // static olarak nesne tanımlanıyor
    private static Db instance = null;
    private Connection connection = null;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/tourismagency";
    private final String DB_USERNAME = "postgres";
    private final String DB_PASS = "1234";

// Constructer'ı private yazıyoruz dışardan ulaşılmasın diye
    private Db() {
        try {
            this.connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    // nesne üretmeden metoda ulaşmak istiyoruz ondan static kullanıyoruz

    //Singleton design pattern
    //hafızada birkere oluşsun her defasında oluşanı versin

    public static Connection getInstance() {

        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Db();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return instance.getConnection();

    }

}
