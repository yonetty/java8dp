package example.java8dp.behavior.cor.old;

import example.java8dp.behavior.cor.Approval;

public class Bucho extends AbstractApprover {

    public Bucho(String name, AbstractApprover next) {
        super(name, next);
    }

    @Override
    protected Approval doApprove(int amount) {
        if (amount <= 100000) {
            System.out.println(String.format("部長決裁(%s) %,3d円", name, amount));
            return new Approval(name, amount);
        } else {
            return null;
        }
    }
}
