package Bashiru.com.Loan_Management_System.Service;


import Bashiru.com.Loan_Management_System.Model.UserData;


public interface UserService {

    public UserData registerUser(UserData user) ;
    public UserData loginUser() ;
    public UserData fetchAdminUser() ;

    public void validateUser(UserData user);

    public UserData fetchUser(String email);

}
