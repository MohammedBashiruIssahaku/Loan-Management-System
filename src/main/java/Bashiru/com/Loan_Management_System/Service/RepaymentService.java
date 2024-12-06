package Bashiru.com.Loan_Management_System.Service;

import Bashiru.com.Loan_Management_System.Model.LoanDetails;
import ch.qos.logback.core.joran.sanity.Pair;
import jdk.jshell.spi.ExecutionControl;

public interface RepaymentService {

    public void createRepayment(LoanDetails details);

    public Pair<String,Double> rePaymentPaid(Double amount, Integer loanId, Double totalAmount) throws ExecutionControl.UserException;
}
