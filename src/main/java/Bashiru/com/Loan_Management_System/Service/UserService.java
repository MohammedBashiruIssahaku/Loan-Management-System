package Bashiru.com.Loan_Management_System.Service;


import Bashiru.com.Loan_Management_System.Model.UserData;
import jdk.jshell.spi.ExecutionControl;

public interface UserService {

    public UserData registerUser(UserData user) throws ExecutionControl.UserException;
    public UserData loginUser()  throws ExecutionControl.UserException;
    public UserData fetchAdminUser() ;

    public void validateUser(UserData user);

    public UserData fetchUser(String email);

}
