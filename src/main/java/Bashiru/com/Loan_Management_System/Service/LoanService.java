package Bashiru.com.Loan_Management_System.Service;

import Bashiru.com.Loan_Management_System.Model.LoanDetails;
import Bashiru.com.Loan_Management_System.Model.UserData;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public interface LoanService {

    public LoanDetails createLoan(UserData user, LoanDetails loanDetails);

    public boolean approveOrRejectLoan(Integer loanId, UserData user, String status);

    public List<LoanDetails> getLoanDetails(UserData user);

    public String reassignLoan(Integer loanId) throws ExecutionControl.UserException;

    public String loanPayment(Double amount, Integer loanId) throws ExecutionControl.UserException;
}
