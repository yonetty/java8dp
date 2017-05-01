package example.java8dp.misc.nullobject.old;

import example.java8dp.misc.nullobject.Discount;

public class OrderService {

    private Discount discount;

    public Integer getPrice(Integer amount) {
        if (discount != null) {
            amount -= discount.calcDiscount(amount);
        }
        return amount;
    }

    private Discount discount2 = new Discount() {
        @Override
        public Integer calcDiscount(Integer totalAmount) {
            return 0;
        }
    };

    public Integer getPrice2(Integer amount) {
        amount -= discount2.calcDiscount(amount);
        return amount;
    }

    private Discount discount3 = amount -> 0;

    public Integer getPrice3(Integer amount) {
        amount -= discount3.calcDiscount(amount);
        return amount;
    }
}
