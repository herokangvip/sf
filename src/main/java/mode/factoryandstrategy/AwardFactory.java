package mode.factoryandstrategy;

public class AwardFactory {
    Award getAwardStrategy(String name) {
        if ("jbean".equals(name)) {
            return new JBeanAward();
        } else if ("coupon".equals(name)) {
            return new CouponAward();
        }
        return null;
    }
}
