import core.Helper;
import entity.Role;
import entity.User;
import view.EmployeeView;

public class App {
    public static void main(String[] args) {

        Helper.setTheme();
        //Db.getInstance();
        //LoginView loginView = new LoginView();
       User dummyUser = new User("enes","1234", Role.EMPLOYEE);
//        AdminView adminView = new AdminView(dummyUser);
        EmployeeView employeeView = new EmployeeView(dummyUser);

    }

}
