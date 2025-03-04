package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("BMW", 5);
        Car car2 = new Car("WUWU", 2);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        User userByCar = userService.findUserByCar(car1.getModel(), car1.getSeries());
        if (userByCar != null) {
            System.out.println("Владелец машины: " + userByCar.getFirstName());
        } else {
            System.out.println("Пользователь с такой машиной не найден.");
        }

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCars());
            System.out.println();
        }
        userService.findUserByCar(car2.getModel(), car2.getSeries());
        context.close();
    }
}