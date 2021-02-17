package mode.factoryandstrategy;

public class AwardContext {
    private AwardFactory factory;

    public AwardContext() {
        this.factory = new AwardFactory();
    }

    void doSendAward(String name){
        factory.getAwardStrategy(name).send();
    }
}
