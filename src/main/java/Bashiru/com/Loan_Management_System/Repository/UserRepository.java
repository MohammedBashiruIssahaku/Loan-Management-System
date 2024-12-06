package Bashiru.com.Loan_Management_System.Repository;

import Bashiru.com.Loan_Management_System.Model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<UserData, Integer> {

    UserData findByEmail(String username);

    List<UserData> findByRole(String role);
}
