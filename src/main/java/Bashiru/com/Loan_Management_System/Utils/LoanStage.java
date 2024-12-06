package Bashiru.com.Loan_Management_System.Utils;

public enum LoanStage {

    PENDING("PENDING"),
    APPROVED("APPROVED"),
    PAID("PAID"),
    REJECTED("REJECTED"),
    CANCELLED("CANCELLED"),
    NOT_REQUIRED("NOT_REQUIRED");

    private String value;

    private LoanStage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

