import core.Helper;
import dao.SeasonDao;
import entity.Role;
import entity.Season;
import entity.User;
import view.EmployeeView;
import view.HotelSaveView;
import view.LoginView;

public class App {
    public static void main(String[] args) {

        Helper.setTheme();
        User loginUser = new User("employee","1",Role.EMPLOYEE);
        //LoginView loginView = new LoginView();
        EmployeeView employeeView  = new EmployeeView(loginUser);




    }

}
