package vttp2022.paf.day13bff.services;

public class BFFException extends Exception {

    private String reason;

    public BFFException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
