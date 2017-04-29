package example.java8dp.behavior.cor;

public class Approval {
    private String approver;
    private int amount;

    public Approval(String approver, int amount) {
        this.approver = approver;
        this.amount = amount;
    }

    public String getApprover() {
        return approver;
    }

    public int getAmount() {
        return amount;
    }
}
