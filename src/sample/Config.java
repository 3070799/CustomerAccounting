package sample;

import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;

public class Config {
    //Сдесь нужно указать логин и пароль для входа в программу
    public static String login;
    public static String pass;




    //Сдесь нужно указать данные для базы данных
    //(в вашей базе данных не должна быть таблица с именем "сlients", программа создаст ее автоматически)
    public static String DATABASE_URL;
    public static String USER;
    public static String PASSWORD;

    public static void reedConfig() throws IOException {
        Ini ini = new Ini(new File("config.ini"));
        login = ini.get("header", "login");
        pass = ini.get("header", "pass");
        DATABASE_URL = ini.get("header", "DATABASE_URL");
        USER = ini.get("header", "USER");
        PASSWORD = ini.get("header", "PASSWORD");

    }
}


