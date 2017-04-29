package example.java8dp.behavior.cor.old;

import example.java8dp.behavior.cor.Approval;

public abstract class AbstractApprover {
    private AbstractApprover next;
    protected String name;

    public AbstractApprover(String name, AbstractApprover next) {
        this.name = name;
        this.next = next;
    }

    public final Approval approve(int amount) {
        Approval approval = doApprove(amount);
        if (approval != null) {
            return approval;
        } else if (next != null) {
            return next.approve(amount);
        } else {
            System.out.println(String.format("決裁できません。 %,3d円", amount));
            return null;
        }
    }

    protected abstract Approval doApprove(int amount);
}
