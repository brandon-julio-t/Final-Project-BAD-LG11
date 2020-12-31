import repositories.CustomerRepository;
import repositories.StaffRepository;
import views.LoginForm;

public class Application {
    public static void main(String[] args) {
        new LoginForm(
                new CustomerRepository(),
                new StaffRepository()
        );
    }
}
