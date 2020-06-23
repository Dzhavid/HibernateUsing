package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        List<User> a = userService.getAllUsers();
        System.out.println(a);
        //System.out.println(a.get(0));
        //userService.createUsersTable();
      //  User user = userService.getAllUsers().get(0);
       //userService.saveUser("lk","fsdf",(byte) 4);

    }
}
