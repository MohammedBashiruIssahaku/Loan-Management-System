package Bashiru.com.Loan_Management_System.Repository;

import Bashiru.com.Loan_Management_System.Model.RepaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepaymentRepository extends JpaRepository<RepaymentDetails,Integer> {

    List<RepaymentDetails> findByLoanIdAndStatus(Integer loanId, String status);
}