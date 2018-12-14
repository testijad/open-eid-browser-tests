package ee.testijad.id.card;

import ee.testijad.id.util.Config;

public class IdCard {

    private String pin1;
    private String pin2;
    private String signature;

    public IdCard(String pin1, String pin2) {
        this.pin1 = pin1;
        this.pin2 = pin2;
    }

    public static IdCard getTestCard() {
        return new IdCard(Config.PIN1, Config.PIN2);
    }

    public String getPin1() {
        return pin1;
    }

    public void setPin1(String pin1) {
        this.pin1 = pin1;
    }

    public String getPin2() {
        return pin2;
    }

    public void setPin2(String pin2) {
        this.pin2 = pin2;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

}
