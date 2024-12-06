package Bashiru.com.Loan_Management_System.Service.impl;

import Bashiru.com.Loan_Management_System.Model.UserData;
import Bashiru.com.Loan_Management_System.Repository.UserRepository;
import Bashiru.com.Loan_Management_System.Service.UserService;
import Bashiru.com.Loan_Management_System.Utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserData registerUser(UserData user) {
        user.setVerified(false);
        return userRepository.save(user);
    }

    @Override
    public void validateUser(UserData user) {
        user.setVerified(true);
        userRepository.save(user);
    }

    @Override
    public UserData loginUser() {

        SecurityContext sc  = SecurityContextHolder.getContext();

        Authentication auth  = sc.getAuthentication();

        String userName = auth.getName();

        UserData user = userRepository.findByEmail(userName);

        return user;

    }

    @Override
    public UserData fetchAdminUser() {

        List<UserData> adminUser = userRepository.findByRole(Constant.ROLE_ADMIN);

        if (!Collections.isEmpty(adminUser)) {
            return adminUser.get(0);
        }
        return null;
    }

    @Override
    public UserData fetchUser(String email) {

        return userRepository.findByEmail(email);
    }
}
