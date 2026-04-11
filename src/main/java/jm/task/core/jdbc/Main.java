package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable(); // содзание новой таблицы

        userService.saveUser("Ибрагим", "Курбанов", (byte) 28); //добавление новых пользователей
        userService.saveUser("Николай", "Назаров", (byte) 26);
        userService.saveUser("Алексей", "Дубровский", (byte) 17);
        userService.saveUser("Василий", "Пупкин", (byte) 55);
        userService.saveUser("Леонид", "Агутин", (byte) 31);
        userService.saveUser("Гога", "Додепов", (byte) 24);

        List<User> allUsers = userService.getAllUsers(); //Получение списка всех пользователей

        if (allUsers != null) {            //вывод пользователей на экран
            for (User user : allUsers) {
                System.out.println(user);
            }
        }

//        userService.cleanUsersTable(); // очистка таблицы
//
//        userService.dropUsersTable(); // удаление таблицы
    }
}
